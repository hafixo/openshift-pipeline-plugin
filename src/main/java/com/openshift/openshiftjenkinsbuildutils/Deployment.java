package com.openshift.openshiftjenkinsbuildutils;

import hudson.model.BuildListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.openshift.restclient.IClient;
import com.openshift.restclient.ResourceKind;
import com.openshift.restclient.model.IDeploymentConfig;
import com.openshift.restclient.model.IReplicationController;

public class Deployment {

	
	public static Map<String,IReplicationController> getDeployments(IClient client, String nameSpace, BuildListener listener) {
		// pass in listener in case we want to trace in the future
		HashMap<String,IReplicationController> map = new HashMap<String,IReplicationController>();
    	List<IReplicationController> rcs = client.list(ResourceKind.REPLICATION_CONTROLLER, nameSpace);
    	for (IReplicationController rc : rcs) {
    		map.put(rc.getReplicaSelector().get("deployment"), rc);
    	}
		return map;
	}
	public static Map<String,IDeploymentConfig> getDeploymentConfigs(IClient client, String nameSpace, BuildListener listener) {
		// pass in listener in case we want to trace in the future
		HashMap<String,IDeploymentConfig> map = new HashMap<String,IDeploymentConfig>();
    	List<IDeploymentConfig> dcs = client.list(ResourceKind.DEPLOYMENT_CONFIG, nameSpace);
    	for (IDeploymentConfig dc : dcs) {
    		map.put(dc.getName(), dc);
    	}
		return map;
	}
}
