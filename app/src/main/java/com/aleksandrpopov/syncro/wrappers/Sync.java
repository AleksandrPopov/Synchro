package com.aleksandrpopov.syncro.wrappers;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

public class Sync extends AsyncTask {

    private String url;
    private String inFile;
    private String outFile;
    private ArrayList<SmbFile> s;

    public Sync(String url, String inFile, String outFile) {
        this.url = url;
        this.inFile = inFile;
        this.outFile = outFile;
    }

    public Sync() {

    }
    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            SmbFile[] domains = new SmbFile("smb://192.168.1.111/E/test/").listFiles();
            for (SmbFile file : domains) {
                File name = new File(file.getName());
                if (name.exists()) {
                    SmbFileInputStream out = new SmbFileInputStream(file);
                    FileOutputStream in = new FileOutputStream(name+file.getName());
                    buffer(out, in);
                    Log.i("qwerty", file.toString());
                } else {
                    Log.i("qwerty", "nothing");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void buffer(SmbFileInputStream fisM, FileOutputStream outM) throws IOException {
        final byte[] b = new byte[16384];
        int read = 0;
        while ((read = fisM.read(b, 0, b.length)) > 0) {
            outM.write(b, 0, read);
        }
    }
    private Boolean isEmpty(final File file) {
        return !(file.isDirectory() && (file.list().length > 0));
    }
}
