package com.aleksandrpopov.syncro.wrappers;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

public class FromLinux extends AsyncTask {

    private String url;
    private String inFile;
    private String outFile;

    public FromLinux(String url, String inFile, String outFile) {
        this.url = url;
        this.inFile = inFile;
        this.outFile = outFile;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            SmbFile domains = new SmbFile(url);
            for (SmbFile f : domains.listFiles()) {
                Log.i("quant", f.toString());
            }
            FileInputStream in = new FileInputStream( Environment.getExternalStorageDirectory() + "/360/" + inFile);
            SmbFileOutputStream out = new SmbFileOutputStream(domains +"/E/Java/" + outFile);

            buffer(out, in);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void buffer(SmbFileOutputStream out, FileInputStream in) throws IOException {
        final byte[] b = new byte[16 * 1024];
        int read = 0;
        while ((read = in.read(b, 0, b.length)) > 0) {
            out.write(b, 0, read);

        }
    }
}