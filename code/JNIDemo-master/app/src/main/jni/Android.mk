LOCAL_PATH  :=  $(call my-dir)

include   $(CLEAR_VARS)

LOCAL_CPPFLAGS  += -DANDROID_BUILD_REMOTER -std=c++0x

LOCAL_MODULE  :=  libmytcpaudio 


LOCAL_SRC_FILES  :=  hello.c hx/test.c 

                 
TARGET_PRELINK_MODULES := false



LOCAL_SHARED_LIBRARIES := \
    liblog \



include  $(BUILD_SHARED_LIBRARY)




