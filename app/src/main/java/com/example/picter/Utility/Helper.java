package com.example.picter.Utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.example.picter.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Helper {
    public static Boolean writeToExternalStorage(Context context, String filename, Bitmap bitmap){
        File directory= new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+context.getString(R.string.app_name));
        if (!directory.exists() && !(directory.mkdirs())){
            return false;
        }
        File file=new File(directory.getAbsolutePath()+"/"+filename);
        if(file.exists() && !(file.canWrite())){
            return false;
        }
        try{
            FileOutputStream fos= new FileOutputStream(file);
            return bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
        }catch(FileNotFoundException e){
            e.printStackTrace();
            return false;
        }


    }
    public static File getFileFromExternalStorage(Context context, String filename){
        File directory= new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+context.getString(R.string.app_name));

        File file=new File(directory.getAbsolutePath()+"/"+filename);
        if(!file.exists() || !(file.canRead())){
            return null;
        }
        return file;

    }

}
