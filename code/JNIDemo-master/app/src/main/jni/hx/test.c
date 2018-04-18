#include <stdio.h>

#include "test.h"
#include "test2.h"

#include "android/log.h"
static const char *TAG="JNIDEMO";
#define LOGE(fmt, args...) __android_log_print(ANDROID_LOG_ERROR, TAG, fmt, ##args)

char* hello()
{
//    printf("Hello, World! \n");
    LOGE("Hello, World!");
//    hello2();
    return "fddddd";
}