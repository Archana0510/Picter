package com.picter.Utility;

import android.content.Context;
import android.graphics.Bitmap;

import com.picter.FilterActivity;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.imageprocessors.subfilters.BrightnessSubfilter;
import com.zomato.photofilters.imageprocessors.subfilters.ContrastSubfilter;
import com.zomato.photofilters.imageprocessors.subfilters.SaturationSubfilter;
import com.zomato.photofilters.imageprocessors.subfilters.VignetteSubfilter;

public class TransformImage {
    private static  final int DEFAULT_BRIGHTNESS=70;
    private static  final int DEFAULT_CONTRAST=50;
    private static  final int DEFAULT_SATURATION=5;
    private static  final int DEFAULT_VIGNETTE=100;

    private String mFileName;
    private Bitmap mBitmap;
    private Context mContext;

    private Bitmap BrightnessBitmap;
    private Bitmap ContrastBitmap;
    private Bitmap SaturationBitmap;
    private Bitmap VignetteBitmap;

    public static int FILTER_BRIGHTNESS=0;
    public static int FILTER_CONTRAST=2;
    public static int FILTER_SATURATION=3;
    public static int FILTER_VIGNETTE=1;

    public String getFilename(int filter) {
        if (filter == FILTER_BRIGHTNESS) {
            return mFileName + "_brightness";
        } else if (filter == FILTER_CONTRAST) {
            return mFileName + "_contrast";
        } else if (filter == FILTER_SATURATION) {
            return mFileName + "_saturation";
        } else if (filter == FILTER_VIGNETTE) {
            return mFileName + "_vignette";
        }
        return mFileName;
    }

    public Bitmap getBitmap(int filter) {
        if (filter == FILTER_BRIGHTNESS) {
            return BrightnessBitmap;
        } else if (filter == FILTER_CONTRAST) {
            return ContrastBitmap;
        } else if (filter == FILTER_SATURATION) {
            return SaturationBitmap;
        } else if (filter == FILTER_VIGNETTE) {
            return VignetteBitmap;
        }
        return mBitmap;
    }

    public TransformImage(Context context, Bitmap bitmap) {
        mBitmap = bitmap;
        mContext = context;
        mFileName = "";
    }

        public Bitmap addBrightnessSubFilter(){
            Filter myFilterBrightness = new Filter();
            Bitmap workingBitmap = Bitmap.createBitmap(mBitmap);
            Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888,true);

            myFilterBrightness.addSubFilter(new BrightnessSubfilter(DEFAULT_BRIGHTNESS));
            Bitmap outputImageBrightness = myFilterBrightness.processFilter(mutableBitmap);

            BrightnessBitmap= outputImageBrightness;
            return outputImageBrightness;

        }
        public Bitmap addContrastSubFilter(){
            Filter myFilterContrast = new Filter();
            Bitmap workingBitmap = Bitmap.createBitmap(mBitmap);
            Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888,true);

            myFilterContrast.addSubFilter(new ContrastSubfilter(DEFAULT_CONTRAST));
            Bitmap outputImageContrast = myFilterContrast.processFilter(mutableBitmap);
            ContrastBitmap=outputImageContrast;
            return outputImageContrast;


        }
        public Bitmap addSaturationSubFilter(){
            Filter myFilterSaturation = new Filter();
            Bitmap workingBitmap = Bitmap.createBitmap(mBitmap);
            Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888,true);
            myFilterSaturation.addSubFilter(new SaturationSubfilter(DEFAULT_SATURATION));
            Bitmap outputImageSaturation = myFilterSaturation.processFilter(mutableBitmap);
            SaturationBitmap=outputImageSaturation;
            return outputImageSaturation;

        }
        public Bitmap addVignetteSubFilter(){
            Filter myFilterVignette = new Filter();
            Bitmap workingBitmap = Bitmap.createBitmap(mBitmap);
            Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888,true);
            myFilterVignette.addSubFilter(new VignetteSubfilter(mContext, DEFAULT_VIGNETTE));
            Bitmap outputImageVignette = myFilterVignette.processFilter(mutableBitmap);
            VignetteBitmap=outputImageVignette;
            return outputImageVignette;

        }

    }


