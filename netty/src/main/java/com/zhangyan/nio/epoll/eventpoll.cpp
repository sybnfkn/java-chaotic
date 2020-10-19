// epoll的核心实现对应于一个epoll描述符
struct eventpoll {
    spinlock_t lock;
    struct mutex mtx;
    wait_queue_head_t wq; // sys_epoll_wait() 等待在这里
    // f_op->poll()  使用的, 被其他事件通知机制利用的wait_address
    wait_queue_head_t poll_wait;
    //已就绪的需要检查的epitem 列表
    struct list_head rdllist;
    //保存所有加入到当前epoll的文件对应的epitem
    struct rb_root rbr;
    // 当正在向用户空间复制数据时, 产生的可用文件
    struct epitem *ovflist;
    /* The user that created the eventpoll descriptor */
    struct user_struct *user;
    struct file *file;
    //优化循环检查，避免循环检查中重复的遍历
    int visited;
    struct list_head visited_list_link;
}