import java.util.*;

public class Tree{
   private Map<String, ArrayList<Node>> nodeChildrenMap = new HashMap<>();
   private ArrayList<Node> unExpandedNodeArray = new ArrayList<>();
   
   public Tree(Node start){
      nodeChildrenMap.put(start.getState().getId(), new ArrayList<>(Arrays.asList(start)));
   }
   
   public Tree(){
   }
   
   //specify parent of node reguardless of what the node says
   public void addNode(Node parent, Node node){
      ArrayList<Node> nodeList;
      String parentId = parent.getState().getId();
            
      if((nodeList = nodeChildrenMap.get(parentId)) != null){
         nodeList.add(node);
         addedUnExpandedNode(node);
      }else{
         unExpandedNodeArray.remove(parent);
         nodeChildrenMap.put(parentId, new ArrayList<>(Arrays.asList(node)));
      }
   }
   
   private void addedUnExpandedNode(Node node){
      int totalCost = node.getTotalCost();
      int size = unExpandedNodeArray.size();
      int i = 0;
      
      while((size < i) && (unExpandedNodeArray.get(i).getTotalCost() < totalCost)){
         i++;
      }
      
      unExpandedNodeArray.add(i, node);
   }
   
   //parent of node specified by node
   public void addNode(Node node){
      Node parent = node.getParent();
      addNode(parent, node);
   }
   
   //parent of node specified by node
   public Node removeNode(Node node){
      Node parent = node.getParent();
      ArrayList<Node> nodeList;
      String parentId = parent.getState().getId();
      
      if((nodeList = nodeChildrenMap.get(parentId)) != null){
         nodeList.remove(node);
         return node;
      }else{
         return null;
      }
   }
   
   public ArrayList<Node> getChildern(Node parent){
      return nodeChildrenMap.get(parent.getState().getId());
   }
   
   public ArrayList<Node> getUnexpandedNodes(){
      return unExpandedNodeArray;
   }
   
   //find if state inside of node is expanded
   public boolean isExpanded(Node node){
      return (nodeChildrenMap.get(node.getState().getId()) != null);
   }
   
   //find if state is expanded
   public boolean isExpanded(State state){
      return (nodeChildrenMap.get(state.getId()) != null);
   }
   
   public void printTree(){
      Iterator iterator = nodeChildrenMap.keySet().iterator();
      String key;
      ArrayList<Node> value;
      Node[] nodeArray;
      
      while (iterator.hasNext()){
         key = iterator.next().toString();
         value = nodeChildrenMap.get(key);
         nodeArray = value.toArray(new Node[value.size()]);
         
         for(int i =0; i< nodeArray.length; i++){
            System.out.println("-------------------------------------------------");
            System.out.println("Parent ID: " + key);
            nodeArray[i].printNode();
         }
         
         System.out.println("-------------------------------------------------");
      }
   }
   
   public void printUnexpandedNodes(){
      ArrayList<Node> arrayList = getUnexpandedNodes();
      Node[] nodeArray = arrayList.toArray(new Node[arrayList.size()]);
      
      for(int i =0; i< nodeArray.length; i++){
         System.out.println("-------------------------------------------------");
         nodeArray[i].printNode();
      }
      
      System.out.println("-------------------------------------------------");
   }
}