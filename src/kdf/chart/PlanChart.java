package kdf.chart;

import java.util.HashMap;

import org.dom4j.Document;

public interface PlanChart {
	/**
	 * 生成XML数据
	 * @param chartType 图表类型
	 * Single Value Charts:
	 * 		AngularGauge--AngularGauge,
	 * Single Series Charts:
	 * 		Column 2D Chart--Column2D,
	 * 		Column 3D Chart--Column3D,
	 *		Pie 3D Chart--Pie3D,
	 *		Pie 2D Chart--Pie2D,
	 *		Line 2D Chart--Line,
	 *		Area 2D Chart--Area2D,
	 * Multi-Series Charts:
	 * 		Multi-series Column 2D Chart--MSColumn2D,
	 *		Multi-series Column 3D Chart--MSColumn3D,
	 *		Multi-series Line 2D Chart--MSLine,
	 * Combination Charts:
	 * 		2D Dual Y Combination--MSCombiDY2D,
	 * 		Column 3D + Line Dual Y--MSColumn3DLineDY,
	 * Scroll Charts：
	 * 		Scroll Combination 2D Chart--ScrollCombiDY2D,
	 * @param map 图表对应的参数集合，设为null则使用默认的图表参数
	 * @return Document
	 */
	public Document buildChartXml(String chartType,HashMap map);
}
