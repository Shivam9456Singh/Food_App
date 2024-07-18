package com.martinestudio.demoapp

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.MediaController
import android.widget.VideoView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Home : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var videoView: VideoView  // Declare videoView as a class-level property

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videoView = view.findViewById(R.id.videoCookingView) // Initialize videoView
        val videoUrl: Uri = Uri.parse("android.resource://" + requireContext().packageName + "/" + R.raw.cooking_video)
        videoView.setVideoURI(videoUrl)
        videoView.start()

        videoView.setOnCompletionListener {
            videoView.start()
        }


    }

    override fun onPause() {
        super.onPause()
        if (::videoView.isInitialized) {  // Check if videoView is initialized before using it
            videoView.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        if (::videoView.isInitialized) {  // Check if videoView is initialized before using it
            videoView.start()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
