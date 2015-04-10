package com.company.project.model.req;

public class LocationMessage extends BaseMessage {

	/**
	 * 地理位置维度
	 */
	private Double Location_X;
	/**
	 * 地理位置经度
	 */
	private Double Location_Y;
	/**
	 * 地图缩放大小
	 */
	private String Scale;
	/**
	 * 地理位置信息
	 */
	private String Label;
	/**
	 * @return the location_X
	 */
	public Double getLocation_X() {
		return Location_X;
	}
	/**
	 * @param location_X the location_X to set
	 */
	public void setLocation_X(Double location_X) {
		Location_X = location_X;
	}
	/**
	 * @return the location_Y
	 */
	public Double getLocation_Y() {
		return Location_Y;
	}
	/**
	 * @param location_Y the location_Y to set
	 */
	public void setLocation_Y(Double location_Y) {
		Location_Y = location_Y;
	}
	/**
	 * @return the scale
	 */
	public String getScale() {
		return Scale;
	}
	/**
	 * @param scale the scale to set
	 */
	public void setScale(String scale) {
		Scale = scale;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return Label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		Label = label;
	}
	
	
}
