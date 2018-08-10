package kdf.chart.singleSeries.pie2D.data;

public class PieProperties {
	private Double radius3D;
	private Integer slicingDistance;
	private Integer pieRadius;
	private Integer startingAngle;
	private Boolean enableRotation;
	
	public PieProperties() {
		this.radius3D = null;
		this.slicingDistance = null;
		this.pieRadius = null;
		this.startingAngle = null;
		this.enableRotation = null;
	}

	public PieProperties(Double radius3D, Integer slicingDistance,
			Integer pieRadius, Integer startingAngle, Boolean enableRotation) {
		super();
		this.radius3D = radius3D;
		this.slicingDistance = slicingDistance;
		this.pieRadius = pieRadius;
		this.startingAngle = startingAngle;
		this.enableRotation = enableRotation;
	}

	public Double getRadius3D() {
		return radius3D;
	}

	public void setRadius3D(Double radius3D) {
		this.radius3D = radius3D;
	}

	public Integer getSlicingDistance() {
		return slicingDistance;
	}

	public void setSlicingDistance(Integer slicingDistance) {
		this.slicingDistance = slicingDistance;
	}

	public Integer getPieRadius() {
		return pieRadius;
	}

	public void setPieRadius(Integer pieRadius) {
		this.pieRadius = pieRadius;
	}

	public Integer getStartingAngle() {
		return startingAngle;
	}

	public void setStartingAngle(Integer startingAngle) {
		this.startingAngle = startingAngle;
	}

	public Boolean getEnableRotation() {
		return enableRotation;
	}

	public void setEnableRotation(Boolean enableRotation) {
		this.enableRotation = enableRotation;
	}
	
	
}
