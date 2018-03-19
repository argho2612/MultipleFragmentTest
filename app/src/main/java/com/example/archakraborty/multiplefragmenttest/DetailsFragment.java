package com.example.archakraborty.multiplefragmenttest;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.cameraview.CameraView;
import com.tzutalin.dlib.Constants;
import com.tzutalin.dlib.FaceDet;
import com.tzutalin.dlib.VisionDetRet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by archakraborty on 14-03-2018.
 */

public class DetailsFragment extends Fragment {
     public static DetailsFragment newInstance(int index){
         DetailsFragment f = new DetailsFragment();
         Bundle args = new Bundle();
         args.putInt("index",index);

         f.setArguments(args);
         return f;
     }
    private static final int[] FLASH_OPTIONS = {
            CameraView.FLASH_AUTO,
            CameraView.FLASH_OFF,
            CameraView.FLASH_ON,
    };

    private static final int[] FLASH_ICONS = {
            R.drawable.ic_flash_auto,
            R.drawable.ic_flash_off,
            R.drawable.ic_flash_on,
    };

    private static final int[] FLASH_TITLES = {
            R.string.flash_auto,
            R.string.flash_off,
            R.string.flash_on,
    };

    private int mCurrentFlash;

    private CameraView mCameraView;

    private Handler mBackgroundHandler;


    public int getShowIndex(){
         return getArguments().getInt("index",0);
     }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout for this fragment
        FaceDet faceDet = new FaceDet(Constants.getFaceShapeModelPath());
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        List<VisionDetRet> results = faceDet.detect(bitmap);
        for (final VisionDetRet ret : results) {
            String label = ret.getLabel();
            int rectLeft = ret.getLeft();
            int rectTop = ret.getTop();
            int rectRight = ret.getRight();
            int rectBottom = ret.getBottom();
            // Get 68 landmark points
            ArrayList<Point> landmarks = ret.getFaceLandmarks();
            for (Point point : landmarks) {
                int pointX = point.x;
                int pointY = point.y;
            }
        }
            return inflater.inflate(R.layout.detection, container, false);
        }
}

