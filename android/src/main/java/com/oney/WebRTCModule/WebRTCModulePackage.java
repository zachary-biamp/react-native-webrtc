package com.oney.WebRTCModule;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import org.webrtc.DefaultVideoEncoderFactory;
import org.webrtc.DefaultVideoDecoderFactory;
import org.webrtc.EglBase;
import org.webrtc.HardwareVideoDecoderFactory;
import org.webrtc.VideoDecoderFactory;
import org.webrtc.VideoEncoderFactory;

import java.util.Arrays;
import java.util.List;

public class WebRTCModulePackage implements ReactPackage {
    // TTC specific decoder options
    private final WebRTCModule.Options options = new WebRTCModule.Options();

    @Override
    public List<NativeModule> createNativeModules(
            ReactApplicationContext reactContext) {

        EglBase.Context eglContext = EglUtils.getRootEglBaseContext();
        VideoDecoderFactory decoderFactory = new VideoDecoderFactoryTTC(eglContext);
        VideoEncoderFactory encoderFactory = new DefaultVideoEncoderFactory(
                eglContext,
                /* enableIntelVp8Encoder */ true,
                /* enableH264HighProfile */ false);

        this.options.setVideoDecoderFactory(decoderFactory);
        this.options.setVideoEncoderFactory(encoderFactory);

        return Arrays.<NativeModule>asList(
            new WebRTCModule(reactContext, options)
        );
    }

    @Override
    public List<ViewManager> createViewManagers(
            ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList(
            new RTCVideoViewManager()
        );
    }
}
