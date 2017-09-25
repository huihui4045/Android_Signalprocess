#include <jni.h>
#include <string>
#include "native-lib.h"


int user_id;


extern "C"
JNIEXPORT void JNICALL
Java_com_huihui_signalprocess_Wathcer_createWatcher(JNIEnv *env, jobject instance, jint userId) {

    // TODO

    user_id=userId;


    create_child();

}

void create_child() {

    pid_t pid=fork();

    if (pid < 0) {

        LOGE(" pid=%d ",pid);

    } else if (pid > 0) {

        LOGE("父进程: pid=%d ",pid);
//父进程
    } else if (pid == 0){

        LOGE("子进程开启: pid=%d ",pid);
//        开启线程轮询
        child_start_monitor();
    }
}

void *thread_rt(void *data){

    pid_t pid;

    while((pid==getppid())!=1){

        sleep(2);
        LOGE("循环  %d ",pid);
    }

    LOGE("重新启动父进程");

    execlp("am","am","startservice","--user",user_id,
           "com.huihui.signalprocess/com.huihui.signalprocess.ProcessService",(char*)NULL);
}


void child_start_monitor() {

    pthread_t tid;
    pthread_create(&tid,NULL,thread_rt,NULL);
}
