package com.example.sy001.myapplication;

import android.util.Log;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smackx.pubsub.AccessModel;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.Node;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.PublishModel;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.Subscription;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;
import org.jivesoftware.smackx.xdata.packet.DataForm;

import java.util.List;


/**
 * 消息发布与订阅
 * Created by WangHaiJun on 2015/10/12.
 */
public class CzingPubSubManager {
    public static String TAG = CzingPubSubManager.class.getSimpleName();
    //获得连接
    //private static XMPPConnection connection = CzingConnectionManager.getInstance().getConnection();

    private ConfigureForm cf; //发布订阅配置


    private CzingPubSubManager() {
        //发布订阅配置
        cf = new ConfigureForm(DataForm.Type.submit);
        //Set some params for the topic – according to your requirement
        cf.setPersistentItems(false);//Sets whether items should be persisted in the node.
        cf.setDeliverPayloads(true);//Sets whether the node will deliver payloads with event notifications.
        cf.setAccessModel(AccessModel.open);
        cf.setPublishModel(PublishModel.open);
        cf.setSubscribe(true);//Sets whether subscriptions are allowed.
    }

    private static class Holder {
        static final CzingPubSubManager instance = new CzingPubSubManager();
    }

    public static CzingPubSubManager getInstance() {
        return Holder.instance;
    }

    //消息发布
    public void pub(String msg, XMPPConnection connection) {
        try {
            PubSubManager psManager = new PubSubManager(connection);
            String nodeId = "pubsub_test2_nodeId";
            Node myNode = null;

            try {
                myNode = psManager.getNode(nodeId);//第一次创建以后服务器就有了，以后就不需要重复创建直接获取即可
            } catch (Exception e) {
                e.printStackTrace();
            }

            // DiscoverItems s = psManager.discoverNodes(nodeId);

            if (myNode == null) {
                myNode = psManager.createNode(nodeId);
            }
//            String msg = messageBean.getMessage();
            SimplePayload payload = new SimplePayload("message", "pubsub:test:message", "<message xmlns='pubsub:test:message'><body>" + msg + "</body></message>");
            PayloadItem<SimplePayload> item = new PayloadItem<SimplePayload>("5", payload);
            ((LeafNode) myNode).publish(item);
            Log.i(TAG, "------publish------");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //消息订阅
    public void sub(XMPPConnection connection) {
        try {
            String nodeId = "pubsub_test2_nodeId";
            PubSubManager manager = new PubSubManager(connection);
            Node eventNode = manager.getNode(nodeId);
            eventNode.addItemEventListener(new ItemEventListener<PayloadItem>() {
                public void handlePublishedItems(ItemPublishEvent evt) {
                    for (Object obj : evt.getItems()) {
                        PayloadItem item = (PayloadItem) obj;
                        Log.i(TAG, "------:Payload=" + item.getPayload().toString());
                    }
                }
            });
            eventNode.subscribe(connection.getUser());
            Log.i(TAG, "sub:");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //消息订阅
    public void init(XMPPConnection connection) {
        Log.i(TAG, "---------------:");
        try {
            String nodeId = "pubsub_test2_nodeId";
            PubSubManager manager = new PubSubManager(connection);
            Node eventNode = manager.getNode(nodeId);
            eventNode.addItemEventListener(new ItemEventListener<PayloadItem>() {
                public void handlePublishedItems(ItemPublishEvent evt) {
                    for (Object obj : evt.getItems()) {
                        PayloadItem item = (PayloadItem) obj;
                        Log.i(TAG, "------:Payload=" + item.getPayload().toString());
                    }
                }
            });
            List<Subscription> list = manager.getSubscriptions();
            for (Subscription sub : list) {
                Log.i("--------", "" + sub.getId() + ":" + sub.getJid() + ":" + sub.getElementName() + ":" + sub.getState() + ":" + sub.getNode() + ":" + sub.toXML());
            }
//            eventNode.
            //eventNode.subscribe(connection.getUser());
            Log.i(TAG, "sub:");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
