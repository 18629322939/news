package com.zhuoxin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.Toast;

import com.zhuoxin.able.OnLoadCameraLister;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by admin on 2016/11/14.
 */

public class CameraCallBack implements SurfaceHolder.Callback {

    Camera mCamera;
    OnLoadCameraLister mOnLoadCameraLister;

    public CameraCallBack(OnLoadCameraLister mOnLoadCameraLister) {
        this.mOnLoadCameraLister = mOnLoadCameraLister;
    }


    //    获取相机数量
    public int getNumbers(Context context) {
        int numberOfCameras = Camera.getNumberOfCameras();
        if (numberOfCameras == 0) {
            Toast.makeText(context, "相机不存在", Toast.LENGTH_SHORT).show();
        }
        if (numberOfCameras == 1) {
            return 0;
        }

        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i = 0; i < numberOfCameras; i++) {
//            匹配
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                return i;
            }
        }
        return 0;
    }

    //         设置焦距的方法
    public void setZoom(int zoom) {
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setZoom(zoom);
        mCamera.setParameters(parameters);

    }

    //    拿到最大焦距
    public int getMaxZoom() {
        return mCamera.getParameters().getMaxZoom();
    }

    //    拍照
    public void tackPicture() {
        mCamera.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
//                字节数组转为照片
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//                存照片
                String parent = Environment.getExternalStorageDirectory().getPath();
                File file = new File(parent + File.separator + System.currentTimeMillis() + ".jpg");
                try {
                    FileOutputStream outputStream = new FileOutputStream(file);
                    //                压缩照片
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                    outputStream.close();
                    if (mOnLoadCameraLister != null) {
                        Log.e("---", "===");
                        mOnLoadCameraLister.getBitMap(bitmap);
                    }
                    mCamera.startPreview();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        mCamera.stopPreview();
    }


    //    刚被创建的时候
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
//        打开相机
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
        mCamera = Camera.open();
//        设置参数
        Camera.Parameters parameters = mCamera.getParameters();
//        设置图片质量
        parameters.setJpegQuality(80);
//        设置自动聚焦
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
//        设置闪光灯自动
        List<String> supportedFlashModes = mCamera.getParameters().getSupportedFlashModes();
        if (supportedFlashModes != null) {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
        }
//        设置相机旋转角度
//        parameters.setRotation(180);
        //         设置像素
        parameters.setPictureFormat(ImageFormat.JPEG);
//        保存参数
        mCamera.setParameters(parameters);
        mCamera.setDisplayOrientation(90);
        try {
            mCamera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        开启预览
        mCamera.startPreview();
//        人脸检测
        mCamera.startFaceDetection();

    }

    //    改变的时候
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    //    销毁的时候
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }
}
