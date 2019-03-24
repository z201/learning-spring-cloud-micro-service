package z201.github.io.node;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author z201
 * @Description 菜单的节点
 * @date 2016年12月6日 上午11:34:17
 */
@SuppressWarnings("rawtypes")
public class MenuNode implements Comparable{
    /**
     * 节点id
     */
    private Integer id;

    /**
     * 父节点
     */
    private Integer parentId;

    /**
     * 节点名称
     */
    private String name;


    /**
     * 节点的url
     */
    private String url;

    /**
     * 节点图标
     */
    private String icon;

    /**
     * 子节点的集合
     */
    @JSONField(serialize = true)
    private List<MenuNode> children = new ArrayList<>();

    /**
     * 查询子节点时候的临时集合
     */
    @JSONField(serialize = false)
    private List<MenuNode> linkedList = new ArrayList<>();

    public MenuNode() {
        super();
    }

    public MenuNode(Integer id, Integer parentId) {
        super();
        this.id = id;
        this.parentId = parentId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public static MenuNode createRoot() {
        return new MenuNode(0, -1);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuNode> getChildren() {
    	
        return children;
    }

    public void setChildren(List<MenuNode> children) {
        this.children = children;
    }
    

    @Override
	public String toString() {
		return "MenuNode [id=" + id + ", parentId=" + parentId + ", name=" + name + ", url=" + url + ", icon=" + icon
				+ ", children=" + children + "]";
	}

	@Override
    public int compareTo(Object o) {
        MenuNode menuNode = (MenuNode) o;
        Integer id = menuNode.getId();
        if (id == null) {
        	id = 0;
        }
        return this.id.compareTo(id);
    }

    /**
     * 构建整个菜单树
     *
     * @author z201
     */
    public void buildNodeTree(List<MenuNode> nodeList) {
        for (MenuNode treeNode : nodeList) {
            List<MenuNode> linkedList = treeNode.findChildNodes(nodeList, treeNode.getId());
            if (linkedList.size() > 0) {
                treeNode.setChildren(linkedList);
            }
        }
    }

    /**
     * 查询子节点的集合
     *
     * @author z201
     */
    public List<MenuNode> findChildNodes(List<MenuNode> nodeList, Integer parentId) {
        if (nodeList == null && parentId == null) {
        	 return null;
        }
        for (Iterator<MenuNode> iterator = nodeList.iterator(); iterator.hasNext(); ) {
            MenuNode node = (MenuNode) iterator.next();
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParentId() != 0 && parentId.equals(node.getParentId())) {
                recursionFn(nodeList, node, parentId);
            }
        }
        return linkedList;
    }

    /**
     * 遍历一个节点的子节点
     *
     * @author z201
     */
    public void recursionFn(List<MenuNode> nodeList, MenuNode node, Integer pId) {
        List<MenuNode> childList = getChildList(nodeList, node);// 得到子节点列表
        if (childList.size() > 0) {// 判断是否有子节点
            if (node.getParentId().equals(pId)) {
                linkedList.add(node);
            }
            Iterator<MenuNode> it = childList.iterator();
            while (it.hasNext()) {
                MenuNode n = (MenuNode) it.next();
                recursionFn(nodeList, n, pId);
            }
        } else {
            if (node.getParentId().equals(pId)) {
                linkedList.add(node);
            }
        }
    }

    /**
     * 得到子节点列表
     *
     * @author z201
     */
    private List<MenuNode> getChildList(List<MenuNode> list, MenuNode node) {
        List<MenuNode> nodeList = new ArrayList<MenuNode>();
        Iterator<MenuNode> it = list.iterator();
        while (it.hasNext()) {
            MenuNode n = (MenuNode) it.next();
            if (n.getParentId().equals(node.getId())) {
                nodeList.add(n);
            }
        }
        return nodeList;
    }

    /**
     * 构建菜单列表
     * @date 2017年2月19日 下午11:18:19
     */
	@SuppressWarnings("unchecked")
	public static List<MenuNode> buildMenu(List<MenuNode> nodes) {
        new MenuNode().buildNodeTree(nodes);
    	Iterator<MenuNode> it = nodes.iterator();
		while (it.hasNext()) {
			MenuNode e = it.next(); // 注意了
			if (e.getParentId() != 0) {
				it.remove();
			}
		}
        //对菜单的子菜单进行排序
        for (MenuNode menuNode : nodes) {
            if (menuNode.getChildren() != null && menuNode.getChildren().size() > 0) {
                Collections.sort(menuNode.getChildren());
            }
        }
        Collections.sort(nodes);
        return nodes;
    }
}
