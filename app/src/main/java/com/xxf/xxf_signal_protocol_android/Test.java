package com.xxf.xxf_signal_protocol_android;


import java.security.KeyPair;

public class Test {
//    /**
//     * 主方法
//     * @param args
//     * @throws Exception
//     */
//    public static void main(String[] args) throws Exception {
//        //甲方公钥
//        byte[] publicKey1;
//        //甲方私钥
//        byte[] privateKey1;
//        //甲方本地密钥
//        byte[] key1;
//        //乙方公钥
//        byte[] publicKey2;
//        //乙方私钥
//        byte[] privateKey2;
//        //乙方本地密钥
//        byte[] key2;
//        //乙方本地密钥
//        byte[] key3;
//
//        //初始化密钥
//        //生成甲方密钥对
//        Map<String, Object> keyMap1 = DHUtils.initKey();
//        publicKey1 = DHUtils.getPublicKey(keyMap1);
//        privateKey1 = DHUtils.getPrivateKey(keyMap1);
//        System.out.println("甲方公钥:\n" + Base64.encode(publicKey1));
//        System.out.println("甲方私钥:\n" + Base64.encode(privateKey1));
//        //由甲方公钥产生乙方本地密钥对
//        Map<String, Object> keyMap2 = DHUtils.initKey(publicKey1);
//        publicKey2 = DHUtils.getPublicKey(keyMap2);
//        privateKey2 = DHUtils.getPrivateKey(keyMap2);
//        System.out.println("乙方公钥:\n" + Base64.encode(publicKey2));
//        System.out.println("乙方私钥:\n" + Base64.encode(privateKey2));
//        key1 = DHUtils.getSecretKey(publicKey2, privateKey1);
//        System.out.println("甲方本地密钥:\n" + Base64.encode(key1));
//        key2 = DHUtils.getSecretKey(publicKey1, privateKey2);
//        System.out.println("乙方本地密钥:\n" + Base64.encode(key2));
//        key3 = DHUtils.getSecretKey(publicKey2, privateKey2);
//        System.out.println("乙方测试:\n" + Base64.encode(key3));
//
//
//        DESUtils des = new DESUtils();
//        System.out.println();
//        System.out.println("===甲方向乙方发送加密数据===");
//        String data = "求知若饥，虚心若愚。";
//        System.out.println("原文:\n" + data);
//        System.out.println("---使用甲方本地密钥对数据进行加密---");
//        //使用甲方本地密钥对数据加密
//        String encode1 = des.encryption(data, Base64.encode(key1));
//        System.out.println("加密:\n" + encode1);
//        System.out.println("---使用乙方本地密钥对数据库进行解密---");
//        //使用乙方本地密钥对数据进行解密
//        String decode1 =des.decryption(encode1, Base64.encode(key2));
//        String output1 = new String(decode1);
//        System.out.println("解密:\n" + output1);
//
//    }

//    public static void main(String[] a){
//        String data = "求知若饥，虚心若愚。";
//        String uuid = "37d5aed075525d4fa0fe635231cba447";
//        DESUtils des = new DESUtils();
//        String privateKey ="MHwCAQAwUwYJKoZIhvcNAQMBMEYCIQD2iiluwMyIPPuJrgOT4YBr4H7FMaPKbWc2F+Mrqb4NjwIhAOdmaHVuoanUjbfnqA5LBlYFkF80M+JiSOd69zOKCy27BCICICsasXkw2Wu6Gar7OVVl24NFh3Vi4vf6Yumqk1Q76alh";
//        String publicKey = "MFowMwYJKoZIhvcNAQMBMCYCIQCO1bEKS2NoQg/Dovk2yATZc+O/Gpboa3sSmWRxXAfv2wIBAgMjAAIgfjEG6JJu5a0XWLKtAqm+t/+KEbhrheM8q4H2D1/oqCQ";
//        try {
//            byte[] secretKey = DHUtils.getSecretKey(Base64.decode(publicKey), Base64.decode(privateKey));
////            byte[] decryKey = DHUtils.getSecretKey(Base64.decode(publicKey), Base64.decode(privateKey));
//            String encode1 = des.encryption(data, Base64.encode(secretKey));
//            System.out.println("===甲方向乙方发送加密数据===");
//            System.out.println("原文:\n" + data);
//            System.out.println("---使用甲方本地密钥对数据进行加密---");
//            System.out.println("加密:\n" + encode1);
////            System.out.println("---使用乙方本地密钥对数据库进行解密---");
////            //使用乙方本地密钥对数据进行解密
////            String decode1 =des.decryption(encode1, Base64.encode(key2));
////            String output1 = new String(decode1);
////            System.out.println("解密:\n" + output1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

//    public static void main(String[] arg) {
//
//        //甲方公钥
//        String publicKey1;
//        //甲方私钥
//        String privateKey1;
//        //甲方本地密钥
//        byte[] key1;
//        //乙方公钥
//        String publicKey2;
//        //乙方私钥
//        String privateKey2;
//        //乙方本地密钥
//        byte[] key2;
//
//        try {
//            //初始化密钥
//            //生成甲方密钥对
//            Map<String, String> keyMap1 = DHUtils.initKey();
//            publicKey1 = keyMap1.get(PUBLIC_KEY);
//            privateKey1= keyMap1.get(PRIVATE_KEY);
//            System.out.println("甲方公钥:\n" + publicKey1);
//            System.out.println("甲方私钥:\n" + privateKey1);
//
//            //由甲方公钥产生乙方本地密钥对
//            Map<String, String> keyMap2 = DHUtils.initKey();
//            publicKey2 = keyMap2.get(PUBLIC_KEY);
//            privateKey2 =keyMap2.get(PRIVATE_KEY);
//            System.out.println("乙方公钥:\n" + publicKey2);
//            System.out.println("乙方私钥:\n" + privateKey2);
//
//            key1 = DHUtils.getSecretKey(Base64.decode(publicKey2), Base64.decode(privateKey1));
//            System.out.println("甲方本地密钥:\n" + Base64.encode(key1));
//            key2 = DHUtils.getSecretKey(Base64.decode(publicKey1), Base64.decode(privateKey2));
//            System.out.println("乙方本地密钥:\n" + Base64.encode(key2));
//
////            String data = "求知若饥，虚心若愚。";
////            DESUtils des = new DESUtils();
////            String encode1 = des.encryption(data, Base64.encode(key1));
////            System.out.println("===甲方向乙方发送加密数据===");
////            System.out.println("原文:\n" + data);
////            System.out.println("---使用甲方本地密钥对数据进行加密---");
////            System.out.println("加密:\n" + encode1);
////            System.out.println("---使用乙方本地密钥对数据库进行解密---");
////            //使用乙方本地密钥对数据进行解密
////            String decode1 = des.decryption(encode1, Base64.encode(key2));
////            String output1 = new String(decode1);
////            System.out.println("解密:\n" + output1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public static void main(String[] arg) {
////        String data = "求知若饥，虚心若愚。";
//        String uuid = "d68017880dce4a12abc4bfbebd532400";
//        String data = EncryptionUtils.encodeString("求知若饥，虚心若愚。", uuid);
//        String publickey = "MHswUwYJKoZIhvcNAQMBMEYCIQDFplW9LcyUBf4kr+Zz0Ye0ewgTQjgAF0NRqsI+F5/AZwIhAJeegGrBaQtyRWfIxKXGhK9hZKUnfB/jv1rHX+bTwc7IAyQAAiEAizaWSpR8h8IrHG4wp9J59o5kKXSzl1NeVcrYw6FrnCU";
//        String privatekey = "MHwCAQAwUwYJKoZIhvcNAQMBMEYCIQDFplW9LcyUBf4kr+Zz0Ye0ewgTQjgAF0NRqsI+F5/AZwIhAJeegGrBaQtyRWfIxKXGhK9hZKUnfB/jv1rHX+bTwc7IBCICIC/ZhEolw33RzgjOZfJ7x44bA/0oML0fTcwB4na9s0r3";
//        System.out.println("甲方公钥:\n" + publickey);
//        System.out.println("甲方私钥:\n" + privatekey);
//
//        try {
//            DESUtils des = new DESUtils();
//            byte[] dhKey = DHUtils.getSecretKey(Base64.decode(publickey), Base64.decode(privatekey));
//            String encode1 = des.encryption(uuid, Base64.encode(dhKey));
//            System.out.println("===甲方向乙方发送加密数据===\n"+data);
//            System.out.println("密钥:\n" + Base64.encode(dhKey));
//            System.out.println("加密后:\n" + encode1);
//
//            System.out.println("---使用乙方本地密钥对数据库进行解密---");
//            //使用乙方本地密钥对数据进行解密
//            String decode1 = des.decryption(encode1, Base64.encode(dhKey));
//            System.out.println("加密数据:\n" +decode1);
//            String output1 = EncryptionUtils.decodeString(data, decode1);
//            System.out.println("解密后:\n" + output1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    private static class User {
        public String name;
        public KeyPair keyPair;

        public User(String name, KeyPair keyPair) {
            this.name = name;
            this.keyPair = keyPair;
        }

        public User(String name) throws Exception {
            this.name = name;
            this.keyPair = DHUtils.initRandomKey();
        }

        @Override
        public String toString() {
            return "\name:" + this.name
                    + "\npublicKey:" + Base64.encode(this.keyPair.getPublic().getEncoded())
                    + "\nprivateKey:" + Base64.encode(this.keyPair.getPrivate().getEncoded())
                    ;
        }
    }

    public static void main(String[] arg) throws Exception {

        User A = new User("张三");
        User B = new User("李四");
        System.out.println(A.toString());
        System.out.println(B.toString());

        byte[] jia = DHUtils.getSecretKey(B.keyPair.getPublic().getEncoded(), A.keyPair.getPrivate().getEncoded());
        byte[] jie = DHUtils.getSecretKey(A.keyPair.getPublic().getEncoded(), B.keyPair.getPrivate().getEncoded());

        String jiami_str = Base64.encode(jia);
        String jiemi_str = Base64.encode(jie);
        System.out.println(String.format("\n加密:%s;\n解密:%s  \njia==jie ? %s", jiami_str, jiemi_str, String.valueOf(jiami_str.equals(jiemi_str))));


    }

    public static void main2(String[] arg) {
        String uuid = "23059fce300f40a585ab08c35f3ae6b9";


        String publickey = "MHowUwYJKoZIhvcNAQMBMEYCIQDwgnvK3xgOfKL0+Mt+JFNUELA1Nn45aTk80sNwgpNhjwIhAJ38y2YHq70d1PvQocCubFQ3hftHQ/My4xV2sfXOQn2lAyMAAiAGyR4Dn8Ddlu2qVQCivYja6qsk54z/7plkd6Nr4d0ruA";
        String privatekey = "MHwCAQAwUwYJKoZIhvcNAQMBMEYCIQDwgnvK3xgOfKL0+Mt+JFNUELA1Nn45aTk80sNwgpNhjwIhAJ38y2YHq70d1PvQocCubFQ3hftHQ/My4xV2sfXOQn2lBCICIBItT4UkWzff16vEb5/xRs6+tdaSXpZBLpbbEEO/OfSe";

//        String publickey = "MHowUwYJKoZIhvcNAQMBMEYCIQDBNbTOPR4+AVhYIQfjrKSzI5STMXaIjfpox28cZQX59wIhAIxoG9UCuweJEZyhi/iFpBEY7sTvSFkToP4LU2TNjPWEAyMAAiB9J1AE6mAf/hFltHdu5/7VDZ8CQslFUYWDidxA3WrH7w";
//        String privatekey = "MHwCAQAwUwYJKoZIhvcNAQMBMEYCIQDBNbTOPR4+AVhYIQfjrKSzI5STMXaIjfpox28cZQX59wIhAIxoG9UCuweJEZyhi/iFpBEY7sTvSFkToP4LU2TNjPWEBCICIF1NXR8T5nbyrnlwb0FTKyTEON/yQgiiOeCg6wHFNyGp";

        String publickey2 = "MHswUwYJKoZIhvcNAQMBMEYCIQDBNbTOPR4+AVhYIQfjrKSzI5STMXaIjfpox28cZQX59wIhAIxoG9UCuweJEZyhi/iFpBEY7sTvSFkToP4LU2TNjPWEAyQAAiEAuta/ot51QuD5QgBa9kLlxIBZN8Eptn9GXkt3Okmd4hw";
        String privatekey2 = "MHwCAQAwUwYJKoZIhvcNAQMBMEYCIQDBNbTOPR4+AVhYIQfjrKSzI5STMXaIjfpox28cZQX59wIhAIxoG9UCuweJEZyhi/iFpBEY7sTvSFkToP4LU2TNjPWEBCICIFR2SxTtsibpSeudlFQD4TLmWNeX6QKakhE3Drx+AckW";

        try {
            System.out.println("uuid = : " + uuid);
            DESUtils des = new DESUtils();
            byte[] dhKey = DHUtils.getSecretKey(Base64.decode(publickey2), Base64.decode(privatekey));
            byte[] dhKey2 = DHUtils.getSecretKey(Base64.decode(publickey), Base64.decode(privatekey2));
            System.out.println("dhkey = " + Base64.encode(dhKey));
            System.out.println("dhkey2 = " + Base64.encode(dhKey2));
//            // a -> a 加密
//            String filekey = des.encryption(uuid, Base64.encode(dhKey));
//            System.out.println("加密后 filekey = : " + filekey);
//            String decryption_uuid = des.decryption(filekey, Base64.encode(dhKey));
//            System.out.println("解密后 uuid = : " + decryption_uuid);
//
//
//
//            byte[] dhKey2 = DHUtils.getSecretKey(Base64.decode(userB.publicKey), Base64.decode(privatekey));
//            System.out.println("dhkey2 : " + Base64.encode(dhKey2));
//            String filekey2 = des.encryption(decryption_uuid, Base64.encode(dhKey2));
//            System.out.println("再加密后 filekey2 = : " + filekey2);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
