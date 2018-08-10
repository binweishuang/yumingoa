package kdf.chart.singleSeries.pie3D.data;

public class PieProperties {
	private Integer slicingDistance;
	private Integer pieRadius;
	private Integer startingAngle;
	private Boolean enableRotation;
	private Integer pieInnerFaceAlpha;
	private Integer pieOuterFaceAlpha;
	private Integer pieYScale;
	private Integer pieSliceDepth;
	
	public PieProperties() {
		this.slicingDistance = null;
		this.pieRadius = null;
		this.startingAngle = null;
		this.enableRotation = null;
		this.pieInnerFaceAlpha = null;
		this.pieOuterFaceAlpha = null;
		this.pieYScale = null;
		this.pieSliceDepth = null;
	}

	public PieProperties(Integer slicingDistance, Integer pieRadius,
			Integer startingAngle, Boolean enableRotation,
			Integer pieInnerFaceAlpha, Integer pieOuterFaceAlpha,
			Integer pieYScale, Integer pieSliceDepth) {
		super();
		this.slicingDistance = slicingDistance;
		this.pieRadius = pieRadius;
		this.startingAngle = startingAngle;
		this.enableRotation = enableRotation;
		this.pieInnerFaceAlpha = pieInnerFaceAlpha;
		this.pieOuterFaceAlpha = pieOuterFaceAlpha;
		this.pieYScale = pieYScale;
		this.pieSliceDepth = pieSliceDepth;
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

	public Integer getPieInnerFaceAlpha() {
		return pieInnerFaceAlpha;
	}

	public void setPieInnerFaceAlpha(Integer pieInnerFaceAlpha) {
		this.pieInnerFaceAlpha = pieInnerFaceAlpha;
	}

	public Integer getPieOuterFaceAlpha() {
		return pieOuterFaceAlpha;
	}

	public void setPieOuterFaceAlpha(Integer pieOuterFaceAlpha) {
		this.pieOuterFaceAlpha = pieOuterFaceAlpha;
	}

	public Integer getPieYScale() {
		return pieYScale;
	}

	public void setPieYScale(Integer pieYScale) {
		this.pieYScale = pieYScale;
	}

	public Integer getPieSliceDepth() {
		return pieSliceDepth;
	}

	public void setPieSliceDepth(Integer pieSliceDepth) {
		this.pieSliceDepth = pieSliceDepth;
	}
	
}
