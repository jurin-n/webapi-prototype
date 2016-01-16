package com.jurin_n;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * JAX-RS をロードするためのクラスです。
 */
@ApplicationPath("/")
public class MyApplication extends Application {
  // GlassFish ではオーバーライドは不要
}
