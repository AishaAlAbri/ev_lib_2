package com.example.user.ev_lib_2;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by training3 on 2/15/16.
 */
public class FileCache {

	private File cacheDir;

	public FileCache(Context context){
		//find thr dir to save cached images
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
			cacheDir=new File(Environment.getExternalStorageDirectory(),"LazyList");


		else
			cacheDir=context.getCacheDir();
		if (!cacheDir.exists())
		cacheDir.mkdir();
	}

public File getFile(String url){
	/// I identfy image by hash code, not perfect solution

	String filename=String.valueOf(url.hashCode());
	File f=new File(cacheDir,filename);
	return f;
}


	public void clear(){
		File[] files=cacheDir.listFiles();
		if(files==null)
			return;
		for(File f:files)
			f.delete();
	}
}
