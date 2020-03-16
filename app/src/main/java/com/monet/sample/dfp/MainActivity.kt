package com.monet.sample.dfp

import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd
import com.monet.bidder.AppMonet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var interstitial: PublisherInterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupAppMonetView()
        setupAppMonetInterstitial()
        loadMrectAd()
        loadInterstitial()
        showInterstitial()
    }

    /**
     * Sets up PublisherInterstitialAd.
     */
    private fun setupAppMonetInterstitial() {
        interstitial = PublisherInterstitialAd(this)
        interstitial.adUnitId = BuildConfig.INTERSTITIAL_AD_UNIT_ID
        interstitial.adListener = object : AdListener() {
            override fun onAdLoaded() {
                showToast("Interstitial Loaded")
            }

            override fun onAdOpened() {
                showToast("Interstitial Shown")
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                showToast("Interstitial Failed")
            }

            override fun onAdClosed() {
                showToast("Interstitial Dismissed")
            }

            override fun onAdClicked() {
                showToast("Interstitial Clicked")
            }
        }
    }

    /**
     * Sets up PublisherAdView.
     */
    private fun setupAppMonetView() {
        publisherAdView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                showToast("Interstitial Loaded")
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                showToast("Interstitial Failed")
            }

            override fun onAdClicked() {
                showToast("Interstitial Clicked")
            }
        }
    }

    /**
     * Listener on mrect button that will trigger AppMonet's addBids method.
     */
    private fun loadMrectAd() {
        loadMrect.setOnClickListener {
            val adRequest = PublisherAdRequest.Builder().build()
            AppMonet.addBids(
                publisherAdView, adRequest, 1500
            ) { publisherAdRequest ->
                publisherAdView.loadAd(publisherAdRequest)
            }
        }
    }

    /**
     * Listener on load interstitial button that will trigger load on PublisherInterstitialAd.
     */
    private fun loadInterstitial() {
        loadInterstitial.setOnClickListener {
            val adRequest = PublisherAdRequest.Builder().build()
            interstitial.loadAd(adRequest)
        }
    }

    /**
     * Listener on show interstitial button that will trigger show on PublisherInterstitialAd.
     */
    private fun showInterstitial() {
        showInterstitial.setOnClickListener {
            if (interstitial.isLoaded) {
                interstitial.show()
            } else {
                showToast("Interstitial Is Not Ready")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
