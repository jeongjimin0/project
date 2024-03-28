//package com.example.toy;
//
//
//import com.windfire.crypto.wfCryptoAES;
//import com.windfire.agents.data.asys.ext.asysExtArchivePut;
//
//public class Encoder extends asysExtArchivePut {
//    private wfCryptoAES m_wfcryptoAes = null;
//
//    public Encoder() {
//        m_wfcryptoAes = new wfCryptoAES();
//    }
//
//    public boolean putFilter(String srcFile, String params, StringBuffer err, Object pool) {
//        try {
//            m_wfcryptoAes.wfCrypto_AES_EncFileToFile(srcFile, srcFile, "xtorm");
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            err.append(e.getMessage());
//            return false;
//        }
//    }
//
//    public static void main(String[] args) {
//        Encoder encoder = new Encoder();
//
//        String filePath = "C:/test/img1.jpg";
//
//        boolean result = encoder.putFilter(filePath, "", new StringBuffer(""), null);
//
//        System.out.println(result);
//    }
//
//}
