package com.binarywalllabs.socialintegration.constants;

public class AppConstants {

    public enum SharedPreferenceKeys {
        USER_NAME("userName"),
        USER_EMAIL("userEmail"),
        USER_IMAGE_URL("userImageUrl");


        private String value;

        SharedPreferenceKeys(String value) {
            this.value = value;
        }

        public String getKey() {
            return value;
        }
    }
    public static final String CHANNEL_ID = "UCGYlNGlloLLZiAL3zBgbMgQ";

   public static final String GOOGLE_CLIENT_ID = "AIzaSyC5vQuSJsdH_rzeSoUF6XHLZ12zwp_MqSg";

  //  public static final String GOOGLE_CLIENT_ID = "259363497412-41t68rhm7frehk5aeg5m721jbdg21fgn.apps.googleusercontent.com";
}
