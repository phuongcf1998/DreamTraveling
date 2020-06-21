/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.utils;

import java.io.Serializable;

/**
 *
 * @author Yun
 */
public class FaceBookConstant implements Serializable{

    public static String FACEBOOK_APP_ID = "3149141171843956";
    public static String FACEBOOK_APP_SECRET = "73535fa99ff74a7a969e595974a1aa5d";
    public static String FACEBOOK_REDIRECT_URL = "http://localhost:8084/TravelingSite/loginWithFaceBook";
    public static String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";

}
