package red_black_tree;

import java.util.ArrayList;

import basic_geometric_entities.Line;
import basic_geometric_entities.LineInstance;


public class RedBlackTree {
	/**
	 * The left child of this node, or EMPTY
	 */
	public RedBlackTree left;

	/**
	 * The right child of this node, or EMPTY
	 */
	public RedBlackTree right;

	/**
	 * The parent of this node, or null
	 */
	public RedBlackTree parent;

	/**
	 * The value stored in this node
	 */
	public LineInstance value;

	/**
	 * The color of this node - red or black (not red)
	 */
	public boolean isRed;

	/**
	 * the unique empty node; used as children on leaf trees and as empty search
	 * trees.
	 */
	public static final RedBlackTree EMPTY = new RedBlackTree();

	/**
	 * A one-time constructor, for constructing empty trees.
	 * 
	 * @post Private constructor that generates the EMPTY node
	 * @return the EMPTY node; leaves have EMPTY as children
	 */
	public RedBlackTree() {
		value = null;
		parent = null;
		left = right = this;
		isRed = false; // empty trees below the leaves should be black
	}

	/**
	 * Constructs a red-black tree with no children, value of the node is
	 * provided by the user
	 * 
	 * @param value
	 *            A (possibly null) value to be referenced by node
	 * @pre v is a non-null Comparable
	 * @post constructs a single node red-black tree
	 */
	public RedBlackTree(LineInstance v) {
		// Assert.pre(v != null, "Red-black tree values must be non-null.");
		value = v;
		parent = null;
		left = right = new RedBlackTree();
		isRed = false; // roots of tree should be colored black
	}

	/**
	 * Determines if this tree is red.
	 * 
	 * @return Whether or not this node is red
	 * @post returns whether or not this node is red
	 */
	public boolean isRed() {
		return isRed;
	}

	/**
	 * Determines if this tree is black.
	 * 
	 * @return Whether or not this node is black
	 * @post returns whether or not this node is black
	 */
	public boolean isBlack() {
		return !isRed;
	}

	/**
	 * Set this node to be a red node
	 * 
	 * @post sets this node red
	 */
	public void setRed() {
		isRed = true;
	}

	/**
	 * Set this node to be a red or black node, depending on value of
	 * <code>isRed</code>.
	 * 
	 * @post sets this node red or black, depending on boolean isRed
	 */
	public void setRed(boolean isRed) {
		this.isRed = isRed;
	}

	/**
	 * Set this node to be black
	 * 
	 * @post sets this node black
	 */
	public void setBlack() {
		isRed = false;
	}

	/**
	 * Returns value associated with this node
	 * 
	 * @post Returns value associated with this node
	 * @return The node's value
	 */
	public LineInstance value() {
		return value;
	}

	/**
	 * Get left subtree of current node
	 * 
	 * @post Returns reference to left subtree, or null
	 * @return The left subtree of this node
	 */
	public RedBlackTree left() {
		return left;
	}

	/**
	 * Get right subtree of current node
	 * 
	 * @post Returns reference to right subtree, or null
	 * @return The right subtree of this node
	 */
	public RedBlackTree right() {
		return right;
	}

	/**
	 * Get reference to parent of this node
	 * 
	 * @post Returns reference to parent node, or null
	 * @return Reference to parent of this node
	 */
	public RedBlackTree parent() {
		return parent;
	}

	/**
	 * Update the parent of this node
	 * 
	 * @post Re-parents this node to parent reference, or null
	 * @param newParent
	 *            A reference to the new parent of this node
	 */
	public void setParent(RedBlackTree newParent) {
		parent = newParent;
	}

	/**
	 * Update the left subtree of this node. Parent of the left subtree is
	 * updated consistently. Existing subtree is detached
	 * 
	 * @pre newLeft is a non-null RedBlackTree node, possibly EMPTY
	 * @post does nothing to the EMPTY node; else makes newLeft a left child of
	 *       this, and this newLeft's parent
	 */
	public void setLeft(RedBlackTree newLeft) {
		if (isEmpty())
			return;
		if (left.parent() == this)
			left.setParent(null);
		left = newLeft;
		left.setParent(this);
	}

	/**
	 * Update the right subtree of this node. Parent of the right subtree is
	 * updated consistently. Existing subtree is detached
	 * 
	 * @pre newRight is a non-null RedBlackTree node, possibly EMPTY
	 * @post does nothing to the EMPTY node; else makes newRight a right child
	 *       of this, and this newRight's parent
	 */
	public void setRight(RedBlackTree newRight) {
		if (isEmpty())
			return;
		if (right.parent() == this)
			right.setParent(null);
		right = newRight;
		right.setParent(this);
	}

	/**
	 * Determine if this node is a left child
	 * 
	 * @post Returns true if this is a left child of parent
	 * @return True iff this node is a left child of parent
	 */
	public boolean isLeftChild() {
		if (parent() == null)
			return false;
		return this == parent().left();
	}

	/**
	 * Determine if this node is a right child
	 * 
	 * @post Returns true if this is a right child of parent
	 * @return True iff this node is a right child of parent
	 */
	public boolean isRightChild() {
		if (parent() == null)
			return false;
		return this == parent().right();
	}

	/**
	 * Returns true if tree is empty.
	 * 
	 * @post Returns true iff the tree rooted at node is empty
	 * @return True iff tree is empty
	 */
	public boolean isEmpty() {
		return value == null;
	}

	/**
	 * Determine if this node is a root.
	 * 
	 * @post Returns true if this is a root node
	 * @return true iff this is a root node
	 */
	public boolean isRoot() {
		return parent == null;
	}

	/**
	 * Returns reference to root of tree containing n
	 * 
	 * @pre this node not EMPTY
	 * @post Returns the root of the tree node n
	 * @return Root of tree
	 */
	public RedBlackTree root() {
		RedBlackTree result = this;
		while (!result.isRoot()) {
			result = result.parent();
		}
		return result;
	}

	/**
	 * Compute the depth of a node. The depth is the path length from node to
	 * root
	 * 
	 * @post Returns the depth of a node in the tree
	 * @return The path length to root of tree
	 */
	public int depth() {
		if (parent() == null)
			return 0;
		return 1 + parent.depth();
	}

	/**
	 * Method to perform a right rotation of tree about this node Node must have
	 * a left child. Relation between left child and node are reversed
	 * 
	 * @pre This node has a left subtree
	 * @post Rotates local portion of tree so left child is root
	 */
	public void rotateRight() {
		// all of this information must be grabbed before
		// any of the references are set. Draw a diagram for help
		RedBlackTree parent = parent();
		RedBlackTree newRoot = left();
		// is the this node a child; if so, a right child?
		boolean wasChild = !isRoot();
		boolean wasLeftChild = isLeftChild();

		// hook in new root (sets newRoot's parent, as well)
		setLeft(newRoot.right());

		// puts pivot below it (sets this's parent, as well)
		newRoot.setRight(this);
		/**
         */

		if (wasChild) {
			if (wasLeftChild)
				parent.setLeft(newRoot);
			else
				parent.setRight(newRoot);
		}
	}

	/**
	 * Method to perform a left rotation of tree about this node Node must have
	 * a right child. Relation between right child and node are reversed
	 * 
	 * @pre This node has a right subtree
	 * @post Rotates local portion of tree so right child is root
	 */
	public void rotateLeft() {
		// all of this information must be grabbed before
		// any of the references are set. Draw a diagram for help
		RedBlackTree parent = parent(); // could be null
		RedBlackTree newRoot = right();
		// is the this node a child; if so, a left child?
		boolean wasChild = !isRoot();
		boolean wasRightChild = isRightChild();

		// hook in new root (sets newRoot's parent, as well)
		setRight(newRoot.left());

		// put pivot below it (sets this's parent, as well)
		newRoot.setLeft(this);

		if (wasChild) {
			if (wasRightChild)
				parent.setRight(newRoot);
			else
				parent.setLeft(newRoot);
		}
		// else
		// Assert.post(newRoot.isRoot(),"Left rotate at root preserves root.");
	}

	/**
	 * Add a value to the red black tree, performing neccessary rotations and
	 * adjustments.
	 * 
	 * @param c
	 *            The value to be added to the tree.
	 * @return The new value of the root.
	 * @pre c is a non-null Comparable value
	 * @post adds a comparable value to the red-black tree; returns the modified
	 *       tree
	 */
	public RedBlackTree add(LineInstance c) {
		RedBlackTree tree = insert(c); // first, do a plain insert
		tree.setRed(); // we insert nodes as red nodes - a first guess
		tree.redFixup(); // now, rebalance the tree
		return tree.root(); // return new root
	}
	

	/**
	 * Insert a (possibly duplicate) value to red-black search tree
	 * 
	 * @param c
	 *            The value to be inserted into the tree.
	 * @pre c is a non-null Comparable value
	 * @post c is inserted into search tree rooted at this
	 */
	public RedBlackTree insert(LineInstance c) {
		// trivial case - tree was empty:
		if (isEmpty())
			return new RedBlackTree(c);

		// decide to insert value to left or right of root:
		if (c.compareTo(value()) < 0) {

			// if to left and no left child, we insert value as leaf
			if (left().isEmpty()) {
				RedBlackTree result = new RedBlackTree(c);
				setLeft(result);
				return result;
			} else {
				// recursively insert to left
				return left().insert(c);
			}
		} else {

			// if to right and no left child, we insert value as leaf
			if (right().isEmpty()) {
				RedBlackTree result = new RedBlackTree(c);
				setRight(result);
				return result;
			} else {
				// recursively insert to right
				return right().insert(c);
			}
		}
	}

	/**
	 * Takes a red node and, restores the red nodes of the tree to maintain
	 * red-black properties if this node has a red parent.
	 * 
	 * @pre this node is a red node; if parent is red, violates property
	 * @post red nodes of the tree are adjusted to maintain properties
	 */
	public void redFixup() {
		if (isRoot() || !parent().isRed()) {
			// ensure that root is black (might have been insertion pt)
			root().setBlack();
		} else {
			RedBlackTree parent = parent(); // we know parent exists
			// since parent is red, it is not root; grandParent exists & black
			RedBlackTree grandParent = parent.parent();
			RedBlackTree aunt; // sibling of parent (may exist)

			if (parent.isLeftChild()) {
				aunt = grandParent.right();
				if (aunt.isRed()) {
					// this:red, parent:red, grand:black, aunt:red
					// push black down from gp to parent-aunt, but
					// coloring gp red may introduce problems higher up
					grandParent.setRed();
					aunt.setBlack();
					parent.setBlack();
					grandParent.redFixup();
				} else {
					if (isRightChild()) {
						// this:red, parent:red, grand:black, aunt:black
						// ensure that this is on outside for later rotate
						parent.rotateLeft();
						parent.redFixup(); // parent is now child of this
					} else {
						// assertion: this is on outside
						// this:red, parent:red, gp: black, aunt:black
						// rotate right @ gp, and make this & gp red sibs
						// under black parent
						grandParent.rotateRight();
						grandParent.setRed();
						parent.setBlack();
					}
				}
			} else // parent.isRightChild()
			{
				aunt = grandParent.left();
				if (aunt.isRed()) {
					// this:red, parent:red, grand:black, aunt:red
					// push black down from gp to parent-aunt, but
					// coloring gp red may introduce problems higher up
					grandParent.setRed();
					aunt.setBlack();
					parent.setBlack();
					grandParent.redFixup();
				} else {
					if (isLeftChild()) {
						// this:red, parent:red, grand:black, aunt:black
						// ensure that this is on outside for later rotate
						parent.rotateRight();
						parent.redFixup(); // parent is now child of this
					} else {
						// assertion: this is on outside
						// this:red, parent:red, gp: black, aunt:black
						// rotate right @ gp, and make this & gp red sibs
						// under black parent
						grandParent.rotateLeft();
						grandParent.setRed();
						parent.setBlack();
					}
				}
			}
		}
	}
	
	
	/**
	 * Remove an value "equals to" the indicated value. Only one value is
	 * removed, and no guarantee is made concerning which of duplicate values
	 * are removed. Value returned is no longer part of the structure
	 * 
	 * @param val
	 *            Value sought to be removed from tree
	 * @return Actual value removed from tree
	 * @pre c is non-null
	 * @post the value is removed; resulting tree is returned
	 */
	public RedBlackTree remove(LineInstance c) {
		// find the target node - the node whose value is removed
		RedBlackTree target = locate(c);
		if (target.isEmpty())
			return root();

		// determine the node to be disconnected:
		// two cases: if degree < 2 we remove target node;
		// otherwise, remove predecessor
		RedBlackTree freeNode;
		if (target.left().isEmpty() || target.right().isEmpty()) // simply
																	// re-root
																	// tree at
																	// right
		{
			// < two children: target node is easily freed
			freeNode = target;
		} else {
			// two children: find predecessor
			freeNode = target.left();
			while (!freeNode.right().isEmpty()) {
				freeNode = freeNode.right();
			}
			// freeNode is predecessor
		}

		target.value = freeNode.value; // move value reference

		// child will be orphaned by the freeing of freeNode;
		// reparent this child carefully (it may be EMPTY)
		RedBlackTree child;
		if (freeNode.left().isEmpty()) {
			child = freeNode.right();
		} else {
			child = freeNode.left();
		}

		// if child is empty, we need to set its parent, temporarily
		child.setParent(freeNode.parent());
		if (!freeNode.isRoot()) {
			if (freeNode.isLeftChild()) {
				freeNode.parent().setLeft(child);
			} else {
				freeNode.parent().setRight(child);
			}
		}

		// Assertion: child has been reparented
		RedBlackTree result = child.root();
		if (freeNode.isBlack())
			child.blackFixup();
		return result.root();
	}

	/**
	 * If a black node has just been removed above this; this node is the root
	 * of a black-height balanced tree, but the ancestors of this node are shy
	 * one black node on this branch. This method restores black-height balance
	 * to such an imbalanced tree.
	 * 
	 * @pre a black node has just been removed above this; this node is the root
	 *      of a black-height balanced tree, but the ancestors of this node are
	 *      shy one black node on this branch
	 * @post the tree is black-height balanced
	 */
	public void blackFixup() {
		// if root - we're actually balanced; if red, set to black
		if (isRoot() || isRed()) {
			setBlack();
		} else {
			RedBlackTree sibling, parent; // temporary refs to relates
			// we hold onto our parent because the nodes shift about
			parent = parent();

			if (isLeftChild()) {
				// our sibling: can't be a leaf (see text)
				sibling = parent.right();

				if (sibling.isRed()) // and, thus, parent is black
				{
					// lower this, but leave black heights the same
					// then reconsider node with a red parent
					sibling.setBlack();
					parent.setRed();
					parent.rotateLeft();
					blackFixup(); // this node might have adopted
				} else if (sibling.left().isBlack()
						&& sibling.right().isBlack()) {
					// sibling black with black children: sib can be red
					// remove sib as one black node in sibling paths, and
					// push missing black problem up to parent
					sibling.setRed();
					parent.blackFixup();
				} else {
					if (sibling.right().isBlack()) {
						// this:black, sib:black, sib.l:red, sib.r:black
						// heighten sibling tree, making sib:r red and
						// sib.l black (both sib.l's children were black)
						sibling.left().setBlack();
						sibling.setRed();
						sibling.rotateRight();
						sibling = parent.right();
					}
					// this: black, sib:black, sib:l black, sib.r:red
					// this tree deepens with parent as new black node
					// sibling holds the previous parent color and
					// sibling color (black) moves down to right;
					// this adds a black node to all paths in this tree
					// so we're done; finish by checking color of root
					sibling.setRed(parent.isRed()); // copy color
					parent.setBlack();
					sibling.right().setBlack();
					parent.rotateLeft();
					root().blackFixup(); // finish by coloring root
				}
			} else { // isRightChild
				// our sibling: can't be a leaf (see text)
				sibling = parent.left();

				if (sibling.isRed()) // and, thus, parent is black
				{
					// lower this, but leave black heights the same
					// then reconsider node with a red parent
					sibling.setBlack();
					parent.setRed();
					parent.rotateRight();
					blackFixup(); // this node might have adopted
				} else if (sibling.left().isBlack()
						&& sibling.right().isBlack()) {
					// sibling black with black children: sib can be red
					// remove sib as one black node in sibling paths, and
					// push missing black problem up to parent
					sibling.setRed();
					parent.blackFixup();
				} else {
					if (sibling.left().isBlack()) {
						// this:black, sib:black, sib.r:red, sib.l:black
						// heighten sibling tree, making sib:l red and
						// sib.r black (both sib.r's children were black)
						sibling.right().setBlack();
						sibling.setRed();
						sibling.rotateLeft();
						sibling = parent.left();
					}
					// this: black, sib:black, sib:r black, sib.l:red
					// this tree deepens with parent as new black node
					// sibling holds the previous parent color and
					// sibling color (black) moves down to left;
					// this adds a black node to all paths in this tree
					// so we're done; finish by checking color of root
					sibling.setRed(parent.isRed()); // copy color
					parent.setBlack();
					sibling.left().setBlack();
					parent.rotateRight();
					root().blackFixup(); // finish by coloring root
				}
			}
		}
	}

	/**
	 * Determines if the red black search tree contains a value
	 * 
	 * @param val
	 *            The value sought. Should be non-null
	 * @return True iff the tree contains a value "equals to" sought value
	 * 
	 * @pre c is non-null
	 * @post returns true iff c is contained within the tree
	 */
	public boolean contains(LineInstance c) {
		return locate(c) != null;
	}

	/**
	 * Locates a value in the search tree or returns the largest value less than
	 * <code>value</code>.
	 * 
	 * @pre c is non-null
	 * @post returns a node of this tree that contains c, or null
	 */
	public RedBlackTree locate(LineInstance c) {
		if (isEmpty())
			return null;
		
		int relation = c.compareTo(value());
		
		if (relation == 0)
			return this;
		if (relation < 0)
			return left().locate(c);
		else
			return right().locate(c);
	}

	/**
	 * Returns a c-equivalent value from tree, or null.
	 * 
	 * @param c
	 *            The c-equivalent value we are looking for in the tree.
	 * @pre c is non-null
	 * @post returns a c-equivalent value from tree, or null
	 */
	public LineInstance get(LineInstance c) {
		RedBlackTree n = locate(c);
		if (n == null)
			return null;
		else
			return n.value();
	}

	/**
	 * Returns true if this node is consistently structured
	 * 
	 * @post returns true if this node is consistently structured
	 */
	public boolean consistency() {
		return /* wellConnected(null) && */redConsistency()
				&& blackConsistency();
	}

	/**
	 * Returns the black height of this subtree.
	 * 
	 * @pre tree is black-height balanced
	 * @post returns the black height of this subtree
	 */
	public int blackHeight() {
		if (isEmpty())
			return 0;
		if (isBlack())
			return 1 + left().blackHeight();
		else
			return left().blackHeight();
	}

	/**
	 * Returns true if no red node in subtree has red children
	 * 
	 * @post returns true if no red node in subtree has red children
	 */
	public boolean redConsistency() {
		if (isEmpty())
			return true;
		if (isRed() && (left().isRed() || right().isRed()))
			return false;
		return left().redConsistency() && right().redConsistency();
	}

	/**
	 * Returns true if black properties of tree are correct
	 * 
	 * @post returns true if black properties of tree are correct
	 */
	public boolean blackConsistency() {
		if (!isRoot()) // must be called on root
		{
			// Assert.debug("Tree consistency not tested at root.");
			return false;
		}
		if (!isBlack()) // root must be black
		{
			// Assert.debug("Root is not black.");
			return false;
		}
		// the number of black nodes on way to any leaf must be same
		if (!consistentlyBlackHeight(blackHeight())) {
			// Assert.debug("Black height inconsistent.");
			return false;
		}
		return true;
	}

	/**
	 * Checks to make sure that the black height of tree is height
	 * 
	 * @post checks to make sure that the black height of tree is height
	 */
	public boolean consistentlyBlackHeight(int height) {
		if (isEmpty())
			return height == 0;
		if (isBlack())
			height--;
		return left().consistentlyBlackHeight(height)
				&& right().consistentlyBlackHeight(height);
	}

	/**
	 * Returns true iff this tree is well-connected.
	 */
	/*
	 * public boolean wellConnected(RedBlackTree<LineInstance> expectedParent) {
	 * boolean ok = true; if (isEmpty()) { if (left != this) { ok = false;
	 * Assert.debug("EMPTY left not self."); } if (right != this) { ok = false;
	 * Assert.debug("EMPTY right not self."); } } else { if (expectedParent !=
	 * parent()) { E expectedParentValue; ok = false; if (expectedParent ==
	 * null) expectedParentValue = "null"; else expectedParentValue =
	 * expectedParent.value(); E parentValue; if (parent == null) parentValue =
	 * "null"; else parentValue = parent.value();
	 * Assert.debug("Parent of "+value
	 * ()+" was not "+expectedParentValue+", but "+parentValue); } if (value ==
	 * null) { ok = false; Assert.debug("null value found in tree"); } ok = ok &
	 * left().wellConnected(this) & right().wellConnected(this); } return ok; }
	 */
	/**
     *
     */
	public void print() {
		if (isEmpty())
			return;
		left().print();
		System.out.println(value());
		right().print();
	}

	public void printNode() {
		if (this.value != null)
			System.out.println(this.value);
		else
			System.out.println("Null!");
	}

	/**
	 * Computes hash code associated with values of tree.
	 * 
	 * @post computes hash code associated with values of tree
	 */
	public int hashCode() {
		if (isEmpty())
			return 0;
		int result = left().hashCode() + right().hashCode();
		if (value() != null)
			result += value().hashCode();
		return result;
	}

	/**
	 * Returns a string representing the tree rooted at this node. <font
	 * color="#FF0000">WARNING</font> this can be a very long string.
	 * 
	 * @return A string representing the tree rooted at this node.
	 */
	public String treeString() {
		String s = "";
		for (int i = 0; i < this.depth(); i++) {
			s += "\t|";
		}

		s += ("<" + value() + " : " + getHand() + " : " + getColor() + ">\n");

		if (left != EMPTY)
			s += left.treeString();
		if (right != EMPTY)
			s += right.treeString();

		return s;
	}

	/**
	 * Support method for {@link #toString}. Returns R if this is node is a
	 * right child, L if this node is a left child and Root if this node is the
	 * root.
	 * 
	 * @return R if this is node is a right child, L if this node is a left
	 *         child and Root if this node is the root.
	 */
	private String getHand() {
		if (isRightChild())
			return "R";
		if (isLeftChild())
			return "L";
		return "Root";
	}

	/**
	 * Support method for {@link #toString}. Returns Red if this is node is a
	 * red, and Black if this node is black.
	 * 
	 * @return R if this is node is a right child, L if this node is a left
	 *         child and Root if this node is the root.
	 */
	private String getColor() {
		if (isRed)
			return "Red";
		return "Black";
	}

	/**
	 * Returns string representation of red-black tree.
	 * 
	 * @pre returns string representation of red-black tree
	 */
	public String toString() {
		if (isEmpty())
			return "";
		if (isRed())
			return "(" + left() + value() + right() + ")";
		else
			return "[" + left() + value() + right() + "]";
	}

	// Implements an in-order traversal of the tree
	public static void inOrder(RedBlackTree root) {
		if (root.value == null)
			return;

		RedBlackTree.inOrder(root.left);

		root.printNode();

		RedBlackTree.inOrder(root.right);
	}
	
	public static Double tempOldY;
	public static void getOldY(LineInstance li, RedBlackTree root) {
		if (root.value == null)
			return;
					
		RedBlackTree.getOldY(li, root.left);
		
		if (root.value.lineId == li.lineId)
			RedBlackTree.tempOldY = root.value.currentY;
		
		RedBlackTree.getOldY(li, root.right);
	}
	public static void getOldYL(Line li, RedBlackTree root) {
	
		if (root.value == null)
			return;
					
		RedBlackTree.getOldYL(li, root.left);
		
		if (root.value.lineId == li.lineId)
			RedBlackTree.tempOldY = root.value.currentY;
		
		RedBlackTree.getOldYL(li, root.right);
	}

	public static RedBlackTree getSuccessor(RedBlackTree root,
			RedBlackTree globalRoot) {
		temp.clear();
		RedBlackTree.getArrayInOrder(globalRoot);
		int rootIndex = temp.indexOf(root);

		if (rootIndex < temp.size() - 1)
			return temp.get(rootIndex + 1);
		else
			return null;
	}

	public LineInstance getSuccessorLine(RedBlackTree globalRoot,
			LineInstance cur) {
		temp.clear();
		RedBlackTree.getArrayInOrder(globalRoot);

		RedBlackTree root = this.locate(cur);

		int rootIndex = temp.indexOf(root);

		if (rootIndex < temp.size() - 1)
			return temp.get(rootIndex + 1).value;
		else
			return null;
	}

	public static RedBlackTree getPredecessor(RedBlackTree root,
			RedBlackTree globalRoot) {
		temp.clear();
		RedBlackTree.getArrayInOrder(globalRoot);
		int rootIndex = temp.indexOf(root);

		if (rootIndex > 0)
			return temp.get(rootIndex - 1);
		else
			return null;
	}

	public LineInstance getPredecessorLine(RedBlackTree globalRoot,
			LineInstance cur) {
		temp.clear();
		RedBlackTree.getArrayInOrder(globalRoot);

		RedBlackTree root = this.locate(cur);

		int rootIndex = temp.indexOf(root);

		if (rootIndex > 0)
			return temp.get(rootIndex - 1).value;
		else
			return null;
	}

	public static ArrayList<RedBlackTree> temp = new ArrayList<RedBlackTree>();

	public static void getArrayInOrder(RedBlackTree root) {

		if (root.value == null)
			return;

		RedBlackTree.getArrayInOrder(root.left);

		temp.add(root);

		RedBlackTree.getArrayInOrder(root.right);
	}

	// Offset goes to first argument.
	public void swapNodes(RedBlackTree r1, RedBlackTree r2, Double offset) {
		this.remove(r1.value);
		this.remove(r2.value);

		r1.value.currentY = r1.value.currentY - offset;
		this.add(r1.value);

		this.add(r2.value);
	}

	// Offset goes to first argument.
	public void swapLines(LineInstance l1, LineInstance l2, Double offset) {
		
		l1.printLine();
		l2.printLine();
		
		
		this.remove(l1);
		this.remove(l2);

		l1.currentY = l1.currentY - offset;
		this.add(l1);

		this.add(l2);
	}
	
	
	/* Removes a line with a specific id. */
	public static RedBlackTree tempNodeWithId;
	public static void removeIDRecur(int lineId, RedBlackTree root) {
		if (root.value == null)
			return;
					
		RedBlackTree.removeIDRecur(lineId, root.left);
		
		if (root.value.lineId == lineId)
			RedBlackTree.tempNodeWithId = root;
		
		RedBlackTree.removeIDRecur(lineId, root.right);
	}
	public RedBlackTree removeByID(int lineId, RedBlackTree root) {
		RedBlackTree.removeIDRecur(lineId, root);
		
		this.remove(RedBlackTree.tempNodeWithId.value);
		return this.root();
	}
	

	
	/* Counts the nodes of the tree rooted at argument passed. */
	public static int size;
	public static void countTreeRec(RedBlackTree root) {
		if (root.value == null)
			return;
					
		RedBlackTree.countTreeRec(root.left);
		
		RedBlackTree.size ++;
		
		RedBlackTree.countTreeRec(root.right);
	}
	public int countTree(RedBlackTree root) {
		RedBlackTree.size = 0;
		
		RedBlackTree.countTreeRec(root);
		
		return RedBlackTree.size;
	}
	
	/* Fill array with line IDs sorted.*/
	public static ArrayList<Integer> lineIDs;
	public static void fillIDsRec(RedBlackTree root) {
		if (root.value == null)
			return;
					
		RedBlackTree.fillIDsRec(root.left);
		
		RedBlackTree.lineIDs.add(root.value.lineId);
		
		RedBlackTree.fillIDsRec(root.right);
	}
	public static ArrayList<Integer> fillIDs(RedBlackTree root) {
		RedBlackTree.lineIDs = new ArrayList<Integer>();
		
		RedBlackTree.fillIDsRec(root);
		
		return RedBlackTree.lineIDs;
	}
	
	
	/* Refresh keys*/
	public static ArrayList<LineInstance> linesToReInsert = new ArrayList<LineInstance>();
	public static RedBlackTree backupRoot;
	public static void refreshKeysRec(RedBlackTree root) {
		if (root.value == null)
			return;
					
		RedBlackTree.refreshKeysRec(root.left);
		
		RedBlackTree.linesToReInsert.add(root.value);
		
		RedBlackTree.refreshKeysRec(root.right);
	}
	
	public RedBlackTree refreshKeys(RedBlackTree root, Double currentX) {
		RedBlackTree ret = new RedBlackTree();
		
		RedBlackTree.linesToReInsert = new ArrayList<LineInstance>();
		
		RedBlackTree.refreshKeysRec(root);
		
		
		// Remove all lines from the tree.
		for (LineInstance li : RedBlackTree.linesToReInsert) {
			root = root.root().remove(li);
			
			li.currentY = li.getY(currentX);
		
			ret = ret.add(li);
		}
		return ret;
	}
	
}