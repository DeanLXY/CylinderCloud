package com.example.cylindercloud.websocket.protocol;

/**
 * Created by Administrator on 2015-9-21.
 */
public class QueueManager {
    private static QueueManager manager = null;
    private RequestQueue<IRequest> queue;

    private QueueManager() {
        queue = new RequestQueue<IRequest>();
    }

    public RequestQueue<IRequest> getQueue() {
        return queue;
    }

    public static QueueManager getManager() {
        if (manager == null) {
            synchronized (QueueManager.class) {
                if (manager == null)
                    manager = new QueueManager();
            }
        }
        return manager;
    }
}
