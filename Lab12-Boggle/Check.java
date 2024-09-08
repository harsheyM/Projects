import java.util.*;
public class Check {
   private CheckNode head; //branches into all unique characters at the first index
   
   private class CheckNode {
      private HashMap<Character,CheckNode> branches;
      private char val;
      public CheckNode(char val) {
         this.val = val;
         this.branches = new HashMap<Character,CheckNode>();
      }
      public void addBranch(CheckNode node) {
         branches.put(node.val,node);
      }
      public CheckNode findBranch(char branch) {
         return branches.get(branch);
      }
      public boolean isLeaf() {
         return branches.size() == 0;
      }
   }
   
   public Check() {
      head = new CheckNode('.');
   }
   
   public void addBranch(String path) {
      CheckNode node = head;
      for (int i = 0; i < path.length(); i++) {
         if (node.findBranch(path.charAt(i)) == null) {
            node.addBranch(new CheckNode(path.charAt(i)));
         }
         node = node.findBranch(path.charAt(i));
      }
   }
   
   public boolean isValidPrefix(String prefix) {
      CheckNode node = head;
      for (int i = 0; i < prefix.length(); i++) {
         if (node == null)
            return false;
         node = node.findBranch(prefix.charAt(i));
      }
      return node != null && !node.isLeaf();
   }
}