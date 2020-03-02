package entity;
/**
 * 分页实体类
 * @author lindy
 */
import java.util.List;

public class Pager {
	// 当前第几页
	private int currpage;
	// 每页显示的条数
	private int pageSize;
	// 总数据条数
	private int totalCount;
	// 总共多少页
	private int totalPage;
	// 分页的数据集合
	private List<StudentInfo> stusList;
	public int getCurrpage() {
		return currpage;
	}
	public void setCurrpage(int currpage) {
		this.currpage = currpage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		if(totalCount > 0) {
			this.totalCount = totalCount;
		}
	}
	public int getTotalPage() {
		// 计算总页数
		if(totalCount % pageSize == 0) {
			totalPage = totalCount / pageSize;
		}else {
			totalPage = totalCount / pageSize + 1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<StudentInfo> getStusList() {
		return stusList;
	}
	public void setStusList(List<StudentInfo> stusList) {
		this.stusList = stusList;
	}
	
}
