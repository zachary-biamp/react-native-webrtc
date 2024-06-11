package com.oney.WebRTCModule;

import androidx.annotation.Nullable;

import org.webrtc.EglBase;
import org.webrtc.PlatformSoftwareVideoDecoderFactory;
import org.webrtc.SoftwareVideoDecoderFactory;
import org.webrtc.VideoCodecInfo;
import org.webrtc.VideoDecoder;
import org.webrtc.VideoDecoderFactory;
import org.webrtc.VideoDecoderFallback;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class VideoDecoderFactoryTTC implements VideoDecoderFactory {
    private final VideoDecoderFactory hardwareVideoDecoderFactory;
    private final VideoDecoderFactory softwareVideoDecoderFactory = new SoftwareVideoDecoderFactory();
    //@Nullable
    //private final VideoDecoderFactory platformSoftwareVideoDecoderFactory;

    public VideoDecoderFactoryTTC(@Nullable EglBase.Context eglContext) {
        this.hardwareVideoDecoderFactory = new HardwareVideoDecoderFactoryTTC(eglContext);
        //this.platformSoftwareVideoDecoderFactory = new PlatformSoftwareVideoDecoderFactory(eglContext);
    }

    @Nullable
    public VideoDecoder createDecoder(VideoCodecInfo codecType) {
        VideoDecoder hardwareDecoder = this.hardwareVideoDecoderFactory.createDecoder(codecType);
        return hardwareDecoder;
        //VideoDecoder softwareDecoder = this.softwareVideoDecoderFactory.createDecoder(codecType);
        // if (softwareDecoder == null && this.platformSoftwareVideoDecoderFactory != null) {
        //     softwareDecoder = this.platformSoftwareVideoDecoderFactory.createDecoder(codecType);
        // }

        // if (hardwareDecoder != null && softwareDecoder != null) {
        //     return new VideoDecoderFallback(softwareDecoder, hardwareDecoder);
        // } else {
        //     return hardwareDecoder != null ? hardwareDecoder : softwareDecoder;
        // }
    }

    public VideoCodecInfo[] getSupportedCodecs() {
        LinkedHashSet<VideoCodecInfo> supportedCodecInfos = new LinkedHashSet();
        //supportedCodecInfos.addAll(Arrays.asList(this.softwareVideoDecoderFactory.getSupportedCodecs()));
        supportedCodecInfos.addAll(Arrays.asList(this.hardwareVideoDecoderFactory.getSupportedCodecs()));
        // if (this.platformSoftwareVideoDecoderFactory != null) {
        //     supportedCodecInfos.addAll(Arrays.asList(this.platformSoftwareVideoDecoderFactory.getSupportedCodecs()));
        // }

        return (VideoCodecInfo[])supportedCodecInfos.toArray(new VideoCodecInfo[supportedCodecInfos.size()]);
    }
}