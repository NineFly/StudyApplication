LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := JNITest
LOCAL_SRC_FILES := test.c
include $(BUILD_SHARED_LIBRARY)