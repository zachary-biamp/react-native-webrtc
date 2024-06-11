package com.oney.WebRTCModule;

import android.media.MediaCodecInfo;

import androidx.annotation.Nullable;

import org.webrtc.EglBase;
import org.webrtc.*;

import android.media.MediaCodecInfo;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.HashMap;



/**
 * Helper class that combines HW and SW decoders.
 */
public class HardwareVideoDecoderFactoryTTC extends HardwareVideoDecoderFactory {
    private static final Predicate<MediaCodecInfo> defaultAllowedPredicate = new Predicate<MediaCodecInfo>() {
        public boolean test(MediaCodecInfo arg) {
            return arg.isHardwareAccelerated();
        }
    };

    public HardwareVideoDecoderFactoryTTC(@Nullable EglBase.Context sharedContext) {
        this(sharedContext, (Predicate)null);
    }
    public HardwareVideoDecoderFactoryTTC(@Nullable EglBase.Context sharedContext, @Nullable Predicate<MediaCodecInfo> codecAllowedPredicate) {
        super(sharedContext, codecAllowedPredicate == null ? defaultAllowedPredicate : codecAllowedPredicate.and(defaultAllowedPredicate));
    }

    @Override
    public VideoCodecInfo[] getSupportedCodecs() {
        Map<String, String> h264_b_params = new HashMap();
        h264_b_params.put("level-asymmetry-allowed", "1");
        h264_b_params.put("packetization-mode", "1");
        h264_b_params.put("profile-level-id", "42001f");

        Map<String, String> h264_m_params = new HashMap();
        h264_m_params.put("level-asymmetry-allowed", "1");
        h264_m_params.put("packetization-mode", "1");
        h264_m_params.put("profile-level-id", "4d001f");


        VideoCodecInfo h264_b = new VideoCodecInfo("H264",h264_b_params);
        VideoCodecInfo h264_m = new VideoCodecInfo("H264",h264_m_params);
        LinkedHashSet<VideoCodecInfo> supportedCodecInfos = new LinkedHashSet<VideoCodecInfo>();
        supportedCodecInfos.add(h264_b);
        supportedCodecInfos.add(h264_m);
        return supportedCodecInfos.toArray(new VideoCodecInfo[supportedCodecInfos.size()]);
    }
}