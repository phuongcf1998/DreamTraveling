/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongntd.utils;

import org.apache.log4j.Logger;

/**
 *
 * @author Yun
 */
public class NewClass {

    static Logger log = Logger.getLogger(NewClass.class.getName());

    public static void main(String[] args) {
        log.trace("This is a Trace");
        log.debug("This is a Debug");
        log.info("This is an Info");
        log.warn("This is a Warn");
        log.error("This is an Error");
        log.fatal("This is a Fatal");
    }
}
