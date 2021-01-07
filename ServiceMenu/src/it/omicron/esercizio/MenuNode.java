package it.omicron.esercizio;


import java.util.List;

public class MenuNode {

	private int nodeId;
	private String nodeName;
	private String nodeType;
	private String groupType;
	private String flowType;
	private String status;
	private long startValidityTs;
	private long endValidityTs;
	private String tag;
	private Resource resource;
	private List<MenuNode> nodes;
	private int maxProfondita = 0;
	private int profondita;

	public MenuNode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MenuNode(int nodeId, String nodeName, String nodeType, String groupType, String flowType, String status,
			long startValidityTs, long endValidityTs, String tag, Resource resource, List<MenuNode> nodes,
			int maxProfondita, int profondita) {
		this.nodeId = nodeId;
		this.nodeName = nodeName;
		this.nodeType = nodeType;
		this.groupType = groupType;
		this.flowType = flowType;
		this.status = status;
		this.startValidityTs = startValidityTs;
		this.endValidityTs = endValidityTs;
		this.tag = tag;
		this.resource = resource;
		this.nodes = nodes;
		this.maxProfondita = maxProfondita;
		this.profondita = profondita;
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getStartValidityTs() {
		return startValidityTs;
	}

	public void setStartValidityTs(long startValidityTs) {
		this.startValidityTs = startValidityTs;
	}

	public long getEndValidityTs() {
		return endValidityTs;
	}

	public void setEndValidityTs(long endValidityTs) {
		this.endValidityTs = endValidityTs;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public List<MenuNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<MenuNode> nodes) {
		this.nodes = nodes;
	}

	public int getMaxProfondita() {
		return maxProfondita;
	}

	public void setMaxProfondita(int maxProfondita) {
		this.maxProfondita = maxProfondita;
	}

	public int getProfondita() {
		return profondita;
	}

	public void setProfondita(int profondita) {
		this.profondita = profondita;
	}

	public void calcoloProfonditaMax(List<MenuNode> list, int currentDepth) {

		if (currentDepth > maxProfondita) {
			maxProfondita = currentDepth;
		}
	
		for (MenuNode currentNode : list) {
			// verifico se è una foglia
			// se è foglia continuo il ciclo
			// else richiamo il metodo calcolaProfodtamacaumentando la currentDepth
			currentNode.setProfondita(currentDepth);
			if (currentNode.getNodes() != null && !currentNode.getNodes().isEmpty()) {
				
				calcoloProfonditaMax(currentNode.getNodes(), currentDepth+1);
			}

		}

	}
	

}
