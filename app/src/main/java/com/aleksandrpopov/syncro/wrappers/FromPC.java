package com.aleksandrpopov.syncro.wrappers;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.FileOutputStream;
import java.io.IOException;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;


public class FromPC extends AsyncTask {

    private String url;
    private String inFile;
    private String outFile;

    public FromPC(String url, String inFile, String outFile) {
        this.url = url;
        this.inFile = inFile;
        this.outFile = outFile;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            SmbFile domains = new SmbFile(url);
            for (SmbFile file : domains.listFiles()) {

            }
            SmbFileInputStream out = new SmbFileInputStream(domains + inFile);
            FileOutputStream in = new FileOutputStream(Environment.getExternalStorageDirectory() + outFile);
            buffer(out, in);

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
}

