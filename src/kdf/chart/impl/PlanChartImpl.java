package kdf.chart.impl;

import java.util.HashMap;
import java.util.List;

import kdf.chart.PlanChart;
import kdf.chart.combination.column3DLineDualY.chart.Anchors;
import kdf.chart.combination.column3DLineDualY.chart.ChartTitles;
import kdf.chart.combination.column3DLineDualY.chart.DivisionalLines;
import kdf.chart.combination.column3DLineDualY.chart.Legend;
import kdf.chart.combination.column3DLineDualY.chart.Tooltip;
import kdf.chart.combination.column3DLineDualY.data.DataPlotCosmetics;
import kdf.chart.combination.column3DLineDualY.others.TrendLines;
import kdf.chart.singleValue.angularGauge.chart.ChartCosmetics;
import kdf.chart.singleValue.angularGauge.chart.ChartPadding;
import kdf.chart.singleValue.angularGauge.chart.FunctionalAttributes;
import kdf.chart.singleValue.angularGauge.chart.Hover;
import kdf.chart.singleValue.angularGauge.chart.Pivot;
import kdf.chart.singleValue.angularGauge.chart.TickMark;
import kdf.chart.singleValue.angularGauge.data.ColorRange;
import kdf.chart.singleValue.angularGauge.data.TrendPoints;
import kdf.chart.singleValue.angularGauge.others.FontProperties;
import kdf.chart.singleValue.angularGauge.others.NumberFormatting;

import org.dom4j.Document;


public class PlanChartImpl implements PlanChart {
	
	private List datas;

	public PlanChartImpl(List datas) {
		super();
		this.datas = datas;
	}

	
	public Document buildChartXml(String chartType,HashMap map) {
		Document doc = null;
		if("Column2D".equals(chartType)) {
			if(null!=map) {
				kdf.chart.singleSeries.column2D.chart.FunctionalAttributes functionalAttributes = null!=map.get("functionalAttributes")?(kdf.chart.singleSeries.column2D.chart.FunctionalAttributes)map.get("functionalAttributes"):null;
				kdf.chart.singleSeries.column2D.chart.ChartCosmetics chartCosmetics = null!=map.get("chartCosmetics")?(kdf.chart.singleSeries.column2D.chart.ChartCosmetics)map.get("chartCosmetics"):null;
				kdf.chart.singleSeries.column2D.chart.ChartPadding chartPadding = null!=map.get("chartPadding")?(kdf.chart.singleSeries.column2D.chart.ChartPadding)map.get("chartPadding"):null;
				kdf.chart.singleSeries.column2D.chart.ChartTitles chartTitles = null!=map.get("chartTitles")?(kdf.chart.singleSeries.column2D.chart.ChartTitles)map.get("chartTitles"):null;
				kdf.chart.singleSeries.column2D.data.DataPlotCosmetics dataPlotCosmetics = null!=map.get("dataPlotCosmetics")?(kdf.chart.singleSeries.column2D.data.DataPlotCosmetics)map.get("dataPlotCosmetics"):null;
				kdf.chart.singleSeries.column2D.chart.DivisionalLines divisionalLines = null!=map.get("divisionalLines")?(kdf.chart.singleSeries.column2D.chart.DivisionalLines)map.get("divisionalLines"):null;
				kdf.chart.singleSeries.column2D.others.FontProperties fontProperties = null!=map.get("fontProperties")?(kdf.chart.singleSeries.column2D.others.FontProperties)map.get("fontProperties"):null;
				kdf.chart.singleSeries.column2D.others.NumberFormatting numberFormatting = null!=map.get("numberFormatting")?(kdf.chart.singleSeries.column2D.others.NumberFormatting)map.get("numberFormatting"):null;
				kdf.chart.singleSeries.column2D.chart.Tooltip tooltip = null!=map.get("tooltip")?(kdf.chart.singleSeries.column2D.chart.Tooltip)map.get("tooltip"):null;
				kdf.chart.singleSeries.column2D.others.TrendLines trendLines = null!=map.get("trendLines")?(kdf.chart.singleSeries.column2D.others.TrendLines)map.get("trendLines"):null;
				doc = new kdf.chart.singleSeries.column2D.PlanColumn2DChart(functionalAttributes,chartCosmetics,chartPadding,chartTitles,dataPlotCosmetics,divisionalLines,fontProperties,numberFormatting,tooltip,trendLines).buildXml(datas);
			} else {
				doc = new kdf.chart.singleSeries.column2D.PlanColumn2DChart().buildXml(datas);
			}
		}
		if("Column3D".equals(chartType)) {
			if(null!=map) {
				kdf.chart.singleSeries.column3D.chart.FunctionalAttributes functionalAttributes = null!=map.get("functionalAttributes")?(kdf.chart.singleSeries.column3D.chart.FunctionalAttributes)map.get("functionalAttributes"):null;
				kdf.chart.singleSeries.column3D.chart.ChartCosmetics chartCosmetics = null!=map.get("chartCosmetics")?(kdf.chart.singleSeries.column3D.chart.ChartCosmetics)map.get("chartCosmetics"):null;
				kdf.chart.singleSeries.column3D.chart.ChartPadding chartPadding = null!=map.get("chartPadding")?(kdf.chart.singleSeries.column3D.chart.ChartPadding)map.get("chartPadding"):null;
				kdf.chart.singleSeries.column3D.chart.ChartTitles chartTitles = null!=map.get("chartTitles")?(kdf.chart.singleSeries.column3D.chart.ChartTitles)map.get("chartTitles"):null;
				kdf.chart.singleSeries.column3D.data.DataPlotCosmetics dataPlotCosmetics = null!=map.get("dataPlotCosmetics")?(kdf.chart.singleSeries.column3D.data.DataPlotCosmetics)map.get("dataPlotCosmetics"):null;
				kdf.chart.singleSeries.column3D.chart.DivisionalLines divisionalLines = null!=map.get("divisionalLines")?(kdf.chart.singleSeries.column3D.chart.DivisionalLines)map.get("divisionalLines"):null;
				kdf.chart.singleSeries.column3D.others.FontProperties fontProperties = null!=map.get("fontProperties")?(kdf.chart.singleSeries.column3D.others.FontProperties)map.get("fontProperties"):null;
				kdf.chart.singleSeries.column3D.others.NumberFormatting numberFormatting = null!=map.get("numberFormatting")?(kdf.chart.singleSeries.column3D.others.NumberFormatting)map.get("numberFormatting"):null;
				kdf.chart.singleSeries.column3D.chart.Tooltip tooltip = null!=map.get("tooltip")?(kdf.chart.singleSeries.column3D.chart.Tooltip)map.get("tooltip"):null;
				kdf.chart.singleSeries.column3D.others.TrendLines trendLines = null!=map.get("trendLines")?(kdf.chart.singleSeries.column3D.others.TrendLines)map.get("trendLines"):null;
				doc = new kdf.chart.singleSeries.column3D.PlanColumn3DChart(functionalAttributes,chartCosmetics,chartPadding,chartTitles,dataPlotCosmetics,divisionalLines,fontProperties,numberFormatting,tooltip,trendLines).buildXml(datas);
			} else {
				doc = new kdf.chart.singleSeries.column3D.PlanColumn3DChart().buildXml(datas);
			}
		}
		if("Pie2D".equals(chartType)) {
			if(null!=map) {
				kdf.chart.singleSeries.pie2D.chart.FunctionalAttributes functionalAttributes = null!=map.get("functionalAttributes")?(kdf.chart.singleSeries.pie2D.chart.FunctionalAttributes)map.get("functionalAttributes"):null;
				kdf.chart.singleSeries.pie2D.chart.ChartCosmetics chartCosmetics = null!=map.get("chartCosmetics")?(kdf.chart.singleSeries.pie2D.chart.ChartCosmetics)map.get("chartCosmetics"):null;
				kdf.chart.singleSeries.pie2D.chart.ChartPadding chartPadding = null!=map.get("chartPadding")?(kdf.chart.singleSeries.pie2D.chart.ChartPadding)map.get("chartPadding"):null;
				kdf.chart.singleSeries.pie2D.chart.ChartTitles chartTitles = null!=map.get("chartTitles")?(kdf.chart.singleSeries.pie2D.chart.ChartTitles)map.get("chartTitles"):null;
				kdf.chart.singleSeries.pie2D.data.DataPlotCosmetics dataPlotCosmetics = null!=map.get("dataPlotCosmetics")?(kdf.chart.singleSeries.pie2D.data.DataPlotCosmetics)map.get("dataPlotCosmetics"):null;
				kdf.chart.singleSeries.pie2D.data.PieProperties pieProperties = null!=map.get("pieProperties")?(kdf.chart.singleSeries.pie2D.data.PieProperties)map.get("pieProperties"):null;
				kdf.chart.singleSeries.pie2D.data.SmartLines smartLines = null!=map.get("smartLines")?(kdf.chart.singleSeries.pie2D.data.SmartLines)map.get("smartLines"):null;
				kdf.chart.singleSeries.pie2D.others.FontProperties fontProperties = null!=map.get("fontProperties")?(kdf.chart.singleSeries.pie2D.others.FontProperties)map.get("fontProperties"):null;
				kdf.chart.singleSeries.pie2D.others.NumberFormatting numberFormatting = null!=map.get("numberFormatting")?(kdf.chart.singleSeries.pie2D.others.NumberFormatting)map.get("numberFormatting"):null;
				kdf.chart.singleSeries.pie2D.chart.Tooltip tooltip = null!=map.get("tooltip")?(kdf.chart.singleSeries.pie2D.chart.Tooltip)map.get("tooltip"):null;
				
				doc = new kdf.chart.singleSeries.pie2D.PlanPie2DChart(functionalAttributes,chartTitles,chartCosmetics,tooltip,chartPadding,dataPlotCosmetics,pieProperties,smartLines,fontProperties,numberFormatting).buildXml(datas);
			} else {
				doc = new kdf.chart.singleSeries.pie2D.PlanPie2DChart().buildXml(datas);
			}
		}
		if("Pie3D".equals(chartType)) {
			if(null!=map) {
				kdf.chart.singleSeries.pie3D.chart.FunctionalAttributes functionalAttributes = null!=map.get("functionalAttributes")?(kdf.chart.singleSeries.pie3D.chart.FunctionalAttributes)map.get("functionalAttributes"):null;
				kdf.chart.singleSeries.pie3D.chart.ChartCosmetics chartCosmetics = null!=map.get("chartCosmetics")?(kdf.chart.singleSeries.pie3D.chart.ChartCosmetics)map.get("chartCosmetics"):null;
				kdf.chart.singleSeries.pie3D.chart.ChartPadding chartPadding = null!=map.get("chartPadding")?(kdf.chart.singleSeries.pie3D.chart.ChartPadding)map.get("chartPadding"):null;
				kdf.chart.singleSeries.pie3D.chart.ChartTitles chartTitles = null!=map.get("chartTitles")?(kdf.chart.singleSeries.pie3D.chart.ChartTitles)map.get("chartTitles"):null;
				kdf.chart.singleSeries.pie3D.data.DataPlotCosmetics dataPlotCosmetics = null!=map.get("dataPlotCosmetics")?(kdf.chart.singleSeries.pie3D.data.DataPlotCosmetics)map.get("dataPlotCosmetics"):null;
				kdf.chart.singleSeries.pie3D.data.PieProperties pieProperties = null!=map.get("pieProperties")?(kdf.chart.singleSeries.pie3D.data.PieProperties)map.get("pieProperties"):null;
				kdf.chart.singleSeries.pie3D.data.SmartLines smartLines = null!=map.get("smartLines")?(kdf.chart.singleSeries.pie3D.data.SmartLines)map.get("smartLines"):null;
				kdf.chart.singleSeries.pie3D.others.FontProperties fontProperties = null!=map.get("fontProperties")?(kdf.chart.singleSeries.pie3D.others.FontProperties)map.get("fontProperties"):null;
				kdf.chart.singleSeries.pie3D.others.NumberFormatting numberFormatting = null!=map.get("numberFormatting")?(kdf.chart.singleSeries.pie3D.others.NumberFormatting)map.get("numberFormatting"):null;
				kdf.chart.singleSeries.pie3D.chart.Tooltip tooltip = null!=map.get("tooltip")?(kdf.chart.singleSeries.pie3D.chart.Tooltip)map.get("tooltip"):null;
				
				doc = new kdf.chart.singleSeries.pie3D.PlanPie3DChart(functionalAttributes,chartTitles,chartCosmetics,tooltip,chartPadding,dataPlotCosmetics,pieProperties,smartLines,fontProperties,numberFormatting).buildXml(datas);
			} else {
				doc = new kdf.chart.singleSeries.pie3D.PlanPie3DChart().buildXml(datas);
			}
		}
		if("Line".equals(chartType)) {
			if(null!=map) {
				kdf.chart.singleSeries.line.chart.FunctionalAttributes functionalAttributes = null!=map.get("functionalAttributes")?(kdf.chart.singleSeries.line.chart.FunctionalAttributes)map.get("functionalAttributes"):null;
				kdf.chart.singleSeries.line.chart.ChartCosmetics chartCosmetics = null!=map.get("chartCosmetics")?(kdf.chart.singleSeries.line.chart.ChartCosmetics)map.get("chartCosmetics"):null;
				kdf.chart.singleSeries.line.chart.ChartPadding chartPadding = null!=map.get("chartPadding")?(kdf.chart.singleSeries.line.chart.ChartPadding)map.get("chartPadding"):null;
				kdf.chart.singleSeries.line.chart.ChartTitles chartTitles = null!=map.get("chartTitles")?(kdf.chart.singleSeries.line.chart.ChartTitles)map.get("chartTitles"):null;
				kdf.chart.singleSeries.line.data.DataPlotCosmetics dataPlotCosmetics = null!=map.get("dataPlotCosmetics")?(kdf.chart.singleSeries.line.data.DataPlotCosmetics)map.get("dataPlotCosmetics"):null;
				kdf.chart.singleSeries.line.chart.DivisionalLines divisionalLines = null!=map.get("divisionalLines")?(kdf.chart.singleSeries.line.chart.DivisionalLines)map.get("divisionalLines"):null;
				kdf.chart.singleSeries.line.others.FontProperties fontProperties = null!=map.get("fontProperties")?(kdf.chart.singleSeries.line.others.FontProperties)map.get("fontProperties"):null;
				kdf.chart.singleSeries.line.others.NumberFormatting numberFormatting = null!=map.get("numberFormatting")?(kdf.chart.singleSeries.line.others.NumberFormatting)map.get("numberFormatting"):null;
				kdf.chart.singleSeries.line.chart.Tooltip tooltip = null!=map.get("tooltip")?(kdf.chart.singleSeries.line.chart.Tooltip)map.get("tooltip"):null;
				kdf.chart.singleSeries.line.others.TrendLines trendLines = null!=map.get("trendLines")?(kdf.chart.singleSeries.line.others.TrendLines)map.get("trendLines"):null;
				kdf.chart.singleSeries.line.chart.Anchors anchors = null!=map.get("anchors")?(kdf.chart.singleSeries.line.chart.Anchors)map.get("anchors"):null;
				doc = new kdf.chart.singleSeries.line.PlanLineChart(functionalAttributes,chartCosmetics,chartPadding,chartTitles,dataPlotCosmetics,divisionalLines,fontProperties,numberFormatting,tooltip,trendLines,anchors).buildXml(datas);
			} else {
				doc = new kdf.chart.singleSeries.line.PlanLineChart().buildXml(datas);
			}
		}
		if("Area2D".equals(chartType)) {
			if(null!=map) {
				kdf.chart.singleSeries.area.chart.FunctionalAttributes functionalAttributes = null!=map.get("functionalAttributes")?(kdf.chart.singleSeries.area.chart.FunctionalAttributes)map.get("functionalAttributes"):null;
				kdf.chart.singleSeries.area.chart.ChartCosmetics chartCosmetics = null!=map.get("chartCosmetics")?(kdf.chart.singleSeries.area.chart.ChartCosmetics)map.get("chartCosmetics"):null;
				kdf.chart.singleSeries.area.chart.ChartPadding chartPadding = null!=map.get("chartPadding")?(kdf.chart.singleSeries.area.chart.ChartPadding)map.get("chartPadding"):null;
				kdf.chart.singleSeries.area.chart.ChartTitles chartTitles = null!=map.get("chartTitles")?(kdf.chart.singleSeries.area.chart.ChartTitles)map.get("chartTitles"):null;
				kdf.chart.singleSeries.area.data.DataPlotCosmetics dataPlotCosmetics = null!=map.get("dataPlotCosmetics")?(kdf.chart.singleSeries.area.data.DataPlotCosmetics)map.get("dataPlotCosmetics"):null;
				kdf.chart.singleSeries.area.chart.DivisionalLines divisionalLines = null!=map.get("divisionalLines")?(kdf.chart.singleSeries.area.chart.DivisionalLines)map.get("divisionalLines"):null;
				kdf.chart.singleSeries.area.others.FontProperties fontProperties = null!=map.get("fontProperties")?(kdf.chart.singleSeries.area.others.FontProperties)map.get("fontProperties"):null;
				kdf.chart.singleSeries.area.others.NumberFormatting numberFormatting = null!=map.get("numberFormatting")?(kdf.chart.singleSeries.area.others.NumberFormatting)map.get("numberFormatting"):null;
				kdf.chart.singleSeries.area.chart.Tooltip tooltip = null!=map.get("tooltip")?(kdf.chart.singleSeries.area.chart.Tooltip)map.get("tooltip"):null;
				kdf.chart.singleSeries.area.others.TrendLines trendLines = null!=map.get("trendLines")?(kdf.chart.singleSeries.area.others.TrendLines)map.get("trendLines"):null;
				kdf.chart.singleSeries.area.chart.Anchors anchors = null!=map.get("anchors")?(kdf.chart.singleSeries.area.chart.Anchors)map.get("anchors"):null;
				doc = new kdf.chart.singleSeries.area.PlanAreaChart(functionalAttributes,chartCosmetics,chartPadding,chartTitles,dataPlotCosmetics,divisionalLines,fontProperties,numberFormatting,tooltip,trendLines,anchors).buildXml(datas);
			} else {
				doc = new kdf.chart.singleSeries.area.PlanAreaChart().buildXml(datas);
			}
		}
		if("MSColumn2D".equals(chartType)) {
			if(null!=map) {
				kdf.chart.multiSeries.column2D.chart.FunctionalAttributes functionalAttributes = null!=map.get("functionalAttributes")?(kdf.chart.multiSeries.column2D.chart.FunctionalAttributes)map.get("functionalAttributes"):null;
				kdf.chart.multiSeries.column2D.chart.ChartCosmetics chartCosmetics = null!=map.get("chartCosmetics")?(kdf.chart.multiSeries.column2D.chart.ChartCosmetics)map.get("chartCosmetics"):null;
				kdf.chart.multiSeries.column2D.chart.ChartPadding chartPadding = null!=map.get("chartPadding")?(kdf.chart.multiSeries.column2D.chart.ChartPadding)map.get("chartPadding"):null;
				kdf.chart.multiSeries.column2D.chart.ChartTitles chartTitles = null!=map.get("chartTitles")?(kdf.chart.multiSeries.column2D.chart.ChartTitles)map.get("chartTitles"):null;
				kdf.chart.multiSeries.column2D.data.DataPlotCosmetics dataPlotCosmetics = null!=map.get("dataPlotCosmetics")?(kdf.chart.multiSeries.column2D.data.DataPlotCosmetics)map.get("dataPlotCosmetics"):null;
				kdf.chart.multiSeries.column2D.chart.DivisionalLines divisionalLines = null!=map.get("divisionalLines")?(kdf.chart.multiSeries.column2D.chart.DivisionalLines)map.get("divisionalLines"):null;
				kdf.chart.multiSeries.column2D.others.FontProperties fontProperties = null!=map.get("fontProperties")?(kdf.chart.multiSeries.column2D.others.FontProperties)map.get("fontProperties"):null;
				kdf.chart.multiSeries.column2D.others.NumberFormatting numberFormatting = null!=map.get("numberFormatting")?(kdf.chart.multiSeries.column2D.others.NumberFormatting)map.get("numberFormatting"):null;
				kdf.chart.multiSeries.column2D.chart.Tooltip tooltip = null!=map.get("tooltip")?(kdf.chart.multiSeries.column2D.chart.Tooltip)map.get("tooltip"):null;
				kdf.chart.multiSeries.column2D.others.TrendLines trendLines = null!=map.get("trendLines")?(kdf.chart.multiSeries.column2D.others.TrendLines)map.get("trendLines"):null;
				kdf.chart.multiSeries.column2D.chart.Legend legend = null!=map.get("legend")?(kdf.chart.multiSeries.column2D.chart.Legend)map.get("legend"):null;
				doc = new kdf.chart.multiSeries.column2D.PlanColumn2DChart(functionalAttributes,chartCosmetics,chartPadding,chartTitles,dataPlotCosmetics,divisionalLines,fontProperties,numberFormatting,tooltip,trendLines,legend).buildXml(datas);
			} else {
				doc = new kdf.chart.multiSeries.column2D.PlanColumn2DChart().buildXml(datas);
			}
		}
		if("MSColumn3D".equals(chartType)) {
			if(null!=map) {
				kdf.chart.multiSeries.column3D.chart.FunctionalAttributes functionalAttributes = null!=map.get("functionalAttributes")?(kdf.chart.multiSeries.column3D.chart.FunctionalAttributes)map.get("functionalAttributes"):null;
				kdf.chart.multiSeries.column3D.chart.ChartCosmetics chartCosmetics = null!=map.get("chartCosmetics")?(kdf.chart.multiSeries.column3D.chart.ChartCosmetics)map.get("chartCosmetics"):null;
				kdf.chart.multiSeries.column3D.chart.ChartPadding chartPadding = null!=map.get("chartPadding")?(kdf.chart.multiSeries.column3D.chart.ChartPadding)map.get("chartPadding"):null;
				kdf.chart.multiSeries.column3D.chart.ChartTitles chartTitles = null!=map.get("chartTitles")?(kdf.chart.multiSeries.column3D.chart.ChartTitles)map.get("chartTitles"):null;
				kdf.chart.multiSeries.column3D.data.DataPlotCosmetics dataPlotCosmetics = null!=map.get("dataPlotCosmetics")?(kdf.chart.multiSeries.column3D.data.DataPlotCosmetics)map.get("dataPlotCosmetics"):null;
				kdf.chart.multiSeries.column3D.chart.DivisionalLines divisionalLines = null!=map.get("divisionalLines")?(kdf.chart.multiSeries.column3D.chart.DivisionalLines)map.get("divisionalLines"):null;
				kdf.chart.multiSeries.column3D.others.FontProperties fontProperties = null!=map.get("fontProperties")?(kdf.chart.multiSeries.column3D.others.FontProperties)map.get("fontProperties"):null;
				kdf.chart.multiSeries.column3D.others.NumberFormatting numberFormatting = null!=map.get("numberFormatting")?(kdf.chart.multiSeries.column3D.others.NumberFormatting)map.get("numberFormatting"):null;
				kdf.chart.multiSeries.column3D.chart.Tooltip tooltip = null!=map.get("tooltip")?(kdf.chart.multiSeries.column3D.chart.Tooltip)map.get("tooltip"):null;
				kdf.chart.multiSeries.column3D.others.TrendLines trendLines = null!=map.get("trendLines")?(kdf.chart.multiSeries.column3D.others.TrendLines)map.get("trendLines"):null;
				kdf.chart.multiSeries.column3D.chart.Legend legend = null!=map.get("legend")?(kdf.chart.multiSeries.column3D.chart.Legend)map.get("legend"):null;
				doc = new kdf.chart.multiSeries.column3D.PlanColumn3DChart(functionalAttributes,chartCosmetics,chartPadding,chartTitles,dataPlotCosmetics,divisionalLines,fontProperties,numberFormatting,tooltip,trendLines,legend).buildXml(datas);
			} else {
				doc = new kdf.chart.multiSeries.column3D.PlanColumn3DChart().buildXml(datas);
			}
		}
		if("MSLine".equals(chartType)) {
			if(null!=map) {
				kdf.chart.multiSeries.line.chart.FunctionalAttributes functionalAttributes = null!=map.get("functionalAttributes")?(kdf.chart.multiSeries.line.chart.FunctionalAttributes)map.get("functionalAttributes"):null;
				kdf.chart.multiSeries.line.chart.ChartCosmetics chartCosmetics = null!=map.get("chartCosmetics")?(kdf.chart.multiSeries.line.chart.ChartCosmetics)map.get("chartCosmetics"):null;
				kdf.chart.multiSeries.line.chart.ChartPadding chartPadding = null!=map.get("chartPadding")?(kdf.chart.multiSeries.line.chart.ChartPadding)map.get("chartPadding"):null;
				kdf.chart.multiSeries.line.chart.ChartTitles chartTitles = null!=map.get("chartTitles")?(kdf.chart.multiSeries.line.chart.ChartTitles)map.get("chartTitles"):null;
				kdf.chart.multiSeries.line.data.DataPlotCosmetics dataPlotCosmetics = null!=map.get("dataPlotCosmetics")?(kdf.chart.multiSeries.line.data.DataPlotCosmetics)map.get("dataPlotCosmetics"):null;
				kdf.chart.multiSeries.line.chart.DivisionalLines divisionalLines = null!=map.get("divisionalLines")?(kdf.chart.multiSeries.line.chart.DivisionalLines)map.get("divisionalLines"):null;
				kdf.chart.multiSeries.line.others.FontProperties fontProperties = null!=map.get("fontProperties")?(kdf.chart.multiSeries.line.others.FontProperties)map.get("fontProperties"):null;
				kdf.chart.multiSeries.line.others.NumberFormatting numberFormatting = null!=map.get("numberFormatting")?(kdf.chart.multiSeries.line.others.NumberFormatting)map.get("numberFormatting"):null;
				kdf.chart.multiSeries.line.chart.Tooltip tooltip = null!=map.get("tooltip")?(kdf.chart.multiSeries.line.chart.Tooltip)map.get("tooltip"):null;
				kdf.chart.multiSeries.line.others.TrendLines trendLines = null!=map.get("trendLines")?(kdf.chart.multiSeries.line.others.TrendLines)map.get("trendLines"):null;
				kdf.chart.multiSeries.line.chart.Legend legend = null!=map.get("legend")?(kdf.chart.multiSeries.line.chart.Legend)map.get("legend"):null;
				kdf.chart.multiSeries.line.chart.Anchors anchors = null!=map.get("anchors")?(kdf.chart.multiSeries.line.chart.Anchors)map.get("anchors"):null;
				doc = new kdf.chart.multiSeries.line.PlanLineChart(functionalAttributes,chartCosmetics,chartPadding,chartTitles,dataPlotCosmetics,divisionalLines,fontProperties,numberFormatting,tooltip,trendLines,anchors,legend).buildXml(datas);
			} else {
				doc = new kdf.chart.multiSeries.line.PlanLineChart().buildXml(datas);
			}
		}
		if("AngularGauge".equals(chartType)) {
			if(null!=map) {
				kdf.chart.singleValue.angularGauge.chart.FunctionalAttributes functionalAttributes = null!=map.get("functionalAttributes")?(kdf.chart.singleValue.angularGauge.chart.FunctionalAttributes)map.get("functionalAttributes"):null;
				kdf.chart.singleValue.angularGauge.chart.ChartCosmetics chartCosmetics = null!=map.get("chartCosmetics")?(kdf.chart.singleValue.angularGauge.chart.ChartCosmetics)map.get("chartCosmetics"):null;
				kdf.chart.singleValue.angularGauge.chart.ChartPadding chartPadding = null!=map.get("chartPadding")?(kdf.chart.singleValue.angularGauge.chart.ChartPadding)map.get("chartPadding"):null;
				kdf.chart.singleValue.angularGauge.chart.Hover hover = null!=map.get("hover")?(kdf.chart.singleValue.angularGauge.chart.Hover)map.get("hover"):null;
				kdf.chart.singleValue.angularGauge.chart.Pivot pivot = null!=map.get("pivot")?(kdf.chart.singleValue.angularGauge.chart.Pivot)map.get("pivot"):null;
				kdf.chart.singleValue.angularGauge.chart.TickMark tickMark = null!=map.get("tickMark")?(kdf.chart.singleValue.angularGauge.chart.TickMark)map.get("tickMark"):null;
				kdf.chart.singleValue.angularGauge.data.ColorRange colorRange = null!=map.get("colorRange")?(kdf.chart.singleValue.angularGauge.data.ColorRange)map.get("colorRange"):null;
				kdf.chart.singleValue.angularGauge.data.TrendPoints trendPoints = null!=map.get("trendPoints")?(kdf.chart.singleValue.angularGauge.data.TrendPoints)map.get("trendPoints"):null;
				kdf.chart.singleValue.angularGauge.others.FontProperties fontProperties = null!=map.get("fontProperties")?(kdf.chart.singleValue.angularGauge.others.FontProperties)map.get("fontProperties"):null;
				kdf.chart.singleValue.angularGauge.others.NumberFormatting numberFormatting = null!=map.get("numberFormatting")?(kdf.chart.singleValue.angularGauge.others.NumberFormatting)map.get("numberFormatting"):null;
				
				doc = new kdf.chart.singleValue.angularGauge.PlanAngularGaugeChart(chartCosmetics,chartPadding,functionalAttributes, hover,pivot, tickMark, fontProperties,numberFormatting, colorRange,trendPoints).buildXml(datas);
			} else {
				doc = new kdf.chart.singleValue.angularGauge.PlanAngularGaugeChart().buildXml(datas);
			}
		}
		if("MSCombiDY2D".equals(chartType)) {
			if(null!=map) {
				kdf.chart.combination.dualY2D.chart.Anchors anchors = null!=map.get("anchors")?(kdf.chart.combination.dualY2D.chart.Anchors)map.get("anchors"):null;
				kdf.chart.combination.dualY2D.chart.ChartCosmetics chartCosmetics = null!=map.get("chartCosmetics")?(kdf.chart.combination.dualY2D.chart.ChartCosmetics)map.get("chartCosmetics"):null;
				kdf.chart.combination.dualY2D.chart.ChartPadding chartPadding = null!=map.get("chartPadding")?(kdf.chart.combination.dualY2D.chart.ChartPadding)map.get("chartPadding"):null;
				kdf.chart.combination.dualY2D.chart.ChartTitles chartTitles = null!=map.get("chartTitles")?(kdf.chart.combination.dualY2D.chart.ChartTitles)map.get("chartTitles"):null;
				kdf.chart.combination.dualY2D.chart.DivisionalLines divisionalLines = null!=map.get("divisionalLines")?(kdf.chart.combination.dualY2D.chart.DivisionalLines)map.get("divisionalLines"):null;
				kdf.chart.combination.dualY2D.chart.FunctionalAttributes functionalAttributes = null!=map.get("functionalAttributes")?(kdf.chart.combination.dualY2D.chart.FunctionalAttributes)map.get("functionalAttributes"):null; 
				kdf.chart.combination.dualY2D.chart.Legend legend = null!=map.get("legend")?(kdf.chart.combination.dualY2D.chart.Legend)map.get("legend"):null;
				kdf.chart.combination.dualY2D.chart.Tooltip tooltip = null!=map.get("tooltip")?(kdf.chart.combination.dualY2D.chart.Tooltip)map.get("tooltip"):null;
				kdf.chart.combination.dualY2D.data.DataPlotCosmetics dataPlotCosmetics = null!=map.get("dataPlotCosmetics")?(kdf.chart.combination.dualY2D.data.DataPlotCosmetics)map.get("dataPlotCosmetics"):null;
				kdf.chart.combination.dualY2D.others.FontProperties fontProperties = null!=map.get("fontProperties")?(kdf.chart.combination.dualY2D.others.FontProperties)map.get("fontProperties"):null;
				kdf.chart.combination.dualY2D.others.NumberFormatting numberFormatting = null!=map.get("numberFormatting")?(kdf.chart.combination.dualY2D.others.NumberFormatting)map.get("numberFormatting"):null;
				kdf.chart.combination.dualY2D.others.TrendLines trendLines = null!=map.get("trendLines")?(kdf.chart.combination.dualY2D.others.TrendLines)map.get("trendLines"):null;
				
				doc = new kdf.chart.combination.dualY2D.Plan2DDualYCombinationChart(functionalAttributes, chartTitles,chartCosmetics, anchors,divisionalLines, legend, tooltip,chartPadding, dataPlotCosmetics,numberFormatting, fontProperties,trendLines).buildXml(datas);
			} else {
				doc = new kdf.chart.combination.dualY2D.Plan2DDualYCombinationChart().buildXml(datas);
			}
		}
		if("MSColumn3DLineDY".equals(chartType)) {
			if(null!=map) {
				kdf.chart.combination.column3DLineDualY.chart.Anchors anchors = null!=map.get("anchors")?(kdf.chart.combination.column3DLineDualY.chart.Anchors)map.get("anchors"):null;
				kdf.chart.combination.column3DLineDualY.chart.ChartCosmetics chartCosmetics = null!=map.get("chartCosmetics")?(kdf.chart.combination.column3DLineDualY.chart.ChartCosmetics)map.get("chartCosmetics"):null;
				kdf.chart.combination.column3DLineDualY.chart.ChartPadding chartPadding = null!=map.get("chartPadding")?(kdf.chart.combination.column3DLineDualY.chart.ChartPadding)map.get("chartPadding"):null;
				kdf.chart.combination.column3DLineDualY.chart.ChartTitles chartTitles = null!=map.get("chartTitles")?(kdf.chart.combination.column3DLineDualY.chart.ChartTitles)map.get("chartTitles"):null;
				kdf.chart.combination.column3DLineDualY.chart.DivisionalLines divisionalLines = null!=map.get("divisionalLines")?(kdf.chart.combination.column3DLineDualY.chart.DivisionalLines)map.get("divisionalLines"):null;
				kdf.chart.combination.column3DLineDualY.chart.FunctionalAttributes functionalAttributes = null!=map.get("functionalAttributes")?(kdf.chart.combination.column3DLineDualY.chart.FunctionalAttributes)map.get("functionalAttributes"):null; 
				kdf.chart.combination.column3DLineDualY.chart.Legend legend = null!=map.get("legend")?(kdf.chart.combination.column3DLineDualY.chart.Legend)map.get("legend"):null;
				kdf.chart.combination.column3DLineDualY.chart.Tooltip tooltip = null!=map.get("tooltip")?(kdf.chart.combination.column3DLineDualY.chart.Tooltip)map.get("tooltip"):null;
				kdf.chart.combination.column3DLineDualY.data.DataPlotCosmetics dataPlotCosmetics = null!=map.get("dataPlotCosmetics")?(kdf.chart.combination.column3DLineDualY.data.DataPlotCosmetics)map.get("dataPlotCosmetics"):null;
				kdf.chart.combination.column3DLineDualY.others.FontProperties fontProperties = null!=map.get("fontProperties")?(kdf.chart.combination.column3DLineDualY.others.FontProperties)map.get("fontProperties"):null;
				kdf.chart.combination.column3DLineDualY.others.NumberFormatting numberFormatting = null!=map.get("numberFormatting")?(kdf.chart.combination.column3DLineDualY.others.NumberFormatting)map.get("numberFormatting"):null;
				kdf.chart.combination.column3DLineDualY.others.TrendLines trendLines = null!=map.get("trendLines")?(kdf.chart.combination.column3DLineDualY.others.TrendLines)map.get("trendLines"):null;
				
				doc = new kdf.chart.combination.column3DLineDualY.PlanColumn3DLineDualYChart(functionalAttributes, chartTitles,chartCosmetics, anchors,divisionalLines, legend, tooltip,chartPadding, dataPlotCosmetics,numberFormatting, fontProperties,trendLines).buildXml(datas);
			} else {
				doc = new kdf.chart.combination.column3DLineDualY.PlanColumn3DLineDualYChart().buildXml(datas);
			}
		}
		if("ScrollCombiDY2D".equals(chartType)) {
			if(null!=map) {
				kdf.chart.scroll.scrollCombiDY2D.chart.Anchors anchors = null!=map.get("anchors")?(kdf.chart.scroll.scrollCombiDY2D.chart.Anchors)map.get("anchors"):null;
				kdf.chart.scroll.scrollCombiDY2D.chart.ChartCosmetics chartCosmetics = null!=map.get("chartCosmetics")?(kdf.chart.scroll.scrollCombiDY2D.chart.ChartCosmetics)map.get("chartCosmetics"):null;
				kdf.chart.scroll.scrollCombiDY2D.chart.ChartPadding chartPadding = null!=map.get("chartPadding")?(kdf.chart.scroll.scrollCombiDY2D.chart.ChartPadding)map.get("chartPadding"):null;
				kdf.chart.scroll.scrollCombiDY2D.chart.ChartTitles chartTitles = null!=map.get("chartTitles")?(kdf.chart.scroll.scrollCombiDY2D.chart.ChartTitles)map.get("chartTitles"):null;
				kdf.chart.scroll.scrollCombiDY2D.chart.ScrollProperties scrollProperties = null!=map.get("scrollProperties")?(kdf.chart.scroll.scrollCombiDY2D.chart.ScrollProperties)map.get("scrollProperties"):null;
				kdf.chart.scroll.scrollCombiDY2D.chart.DivisionalLines divisionalLines = null!=map.get("divisionalLines")?(kdf.chart.scroll.scrollCombiDY2D.chart.DivisionalLines)map.get("divisionalLines"):null;
				kdf.chart.scroll.scrollCombiDY2D.chart.FunctionalAttributes functionalAttributes = null!=map.get("functionalAttributes")?(kdf.chart.scroll.scrollCombiDY2D.chart.FunctionalAttributes)map.get("functionalAttributes"):null; 
				kdf.chart.scroll.scrollCombiDY2D.chart.Legend legend = null!=map.get("legend")?(kdf.chart.scroll.scrollCombiDY2D.chart.Legend)map.get("legend"):null;
				kdf.chart.scroll.scrollCombiDY2D.chart.Tooltip tooltip = null!=map.get("tooltip")?(kdf.chart.scroll.scrollCombiDY2D.chart.Tooltip)map.get("tooltip"):null;
				kdf.chart.scroll.scrollCombiDY2D.data.DataPlotCosmetics dataPlotCosmetics = null!=map.get("dataPlotCosmetics")?(kdf.chart.scroll.scrollCombiDY2D.data.DataPlotCosmetics)map.get("dataPlotCosmetics"):null;
				kdf.chart.scroll.scrollCombiDY2D.others.FontProperties fontProperties = null!=map.get("fontProperties")?(kdf.chart.scroll.scrollCombiDY2D.others.FontProperties)map.get("fontProperties"):null;
				kdf.chart.scroll.scrollCombiDY2D.others.NumberFormatting numberFormatting = null!=map.get("numberFormatting")?(kdf.chart.scroll.scrollCombiDY2D.others.NumberFormatting)map.get("numberFormatting"):null;
				kdf.chart.scroll.scrollCombiDY2D.others.TrendLines trendLines = null!=map.get("trendLines")?(kdf.chart.scroll.scrollCombiDY2D.others.TrendLines)map.get("trendLines"):null;				
				
				doc = new kdf.chart.scroll.scrollCombiDY2D.PlanScrollCombiDY2DChart(functionalAttributes, chartTitles,chartCosmetics, scrollProperties,anchors,divisionalLines, legend, tooltip,chartPadding, dataPlotCosmetics,numberFormatting, fontProperties,trendLines).buildXml(datas);
			} else {
				doc = new kdf.chart.scroll.scrollCombiDY2D.PlanScrollCombiDY2DChart().buildXml(datas);
			}
		}
		return doc;
	}

}
