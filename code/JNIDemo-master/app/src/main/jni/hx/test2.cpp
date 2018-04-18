#include <stdio.h>

#include "test2.h"

#include "android/log.h"
static const char *TAG="JNIDEMO";
#define LOGE(fmt, args...) __android_log_print(ANDROID_LOG_ERROR, TAG, fmt, ##args)

char* hello2()
{
//    printf("Hello, World! \n");
    LOGE("Hello, World!");
    return "fddddd";
}