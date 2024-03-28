//package com.example.toy;
//
//import com.windfire.agents.data.asys.ext.asysExtArchiveGet;
//import com.windfire.crypto.wfCryptoAES;
//
//public class Decoder extends asysExtArchiveGet {
//    private wfCryptoAES m_wfcryptoAes = null;
//    private String decDir = "C:\\Xtorm";
//
//    public Decoder() {
//        m_wfcryptoAes = new wfCryptoAES();
//    }
//
//    public String retrieveFilter(String srcFile, String params, StringBuffer err) {
//    String decFile = decDir + srcFile.substring(srcFile.lastIndexOf("/")) + "_DEC" + Thread.currentThread().getName() + "_" + System.nanoTime();
//
//    try {
//        m_wfcryptoAes.wfCrypto_AES_DecFileToFile(srcFile, decFile, "xtorm");
//    } catch (Exception e) {
//        e.printStackTrace();
//        err.append(e.getMessage());
//        return decFile;
//    }
//
//    return decFile;
//    }
//
//    public static void main(String[] args) {
//        Decoder decoder = new Decoder();
//
//        String filePath = "C:/test/img1.jpg";
//
//        String result = decoder.retrieveFilter(filePath, "", new StringBuffer(""));
//
//        System.out.println(result);
//    }
//}
