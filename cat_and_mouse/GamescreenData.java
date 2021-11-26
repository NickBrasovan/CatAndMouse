package cat_and_mouse;

import java.io.Serializable;

public class GamescreenData implements Serializable {
	
	private int mouse_x;
	private int mouse_y;
	private int req_dx;
	private int req_dy;
	
	public int getReqDX()
	{
		return this.req_dx;
	}
	
	public void setReqDX(int rdx)
	{
		this.req_dx = rdx;
	}
	
	public int getReqDY()
	{
		return this.req_dy;
	}
	
	public void setReqDy(int rdy)
	{
		this.req_dx = rdy;
	}
	
	
	public int getMouseY()
	{
		return this.mouse_y;
	}
	
	public void setMouseX(int x)
	{
		this.mouse_x = x;
	}
	
	public int getMouseX()
	{
		return this.mouse_x;
	}
	
	public void setMouseY(int x)
	{
		this.mouse_x = x;
	}
}
