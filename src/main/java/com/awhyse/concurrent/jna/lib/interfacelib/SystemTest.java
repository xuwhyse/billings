package com.awhyse.concurrent.jna.lib.interfacelib;

import com.sun.jna.Library;

public interface SystemTest extends Library{
	void printf(String format, Object... args);
}

