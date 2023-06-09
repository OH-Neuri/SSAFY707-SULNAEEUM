import * as d3 from "d3";
import cloud from "d3-cloud";
import { useEffect, useRef } from "react";

type WordCloudProps = {
  data: { text: string; value: number }[];
  width: number;
  height: number;
};

const WordCloud: React.FC<WordCloudProps> = ({ data, width, height }) => {
  const svgRef = useRef<SVGSVGElement>(null);

  useEffect(() => {
    if (!svgRef.current) return;

    const layout = cloud()
      .size([width, height])
      .words(data)
      .padding(4.5)
      .rotate(() => 0 )
      .font("sbM")
      .fontSize((d) => {
        if (d['value'] < 5) {
          return 15
        } else if (d['value'] < 10){
          return 25
        }
        return d['value'] * 4
      })
      .on("end", draw);
    layout.start();
  }, [data, width, height]);

  function draw(words: any[]) {
    const svg = d3.select(svgRef.current);
    svg.selectAll("*").remove();

    svg
      .append("g")
      .attr("transform", `translate(${width / 2}, ${height / 2})`)
      .selectAll("text")
      .data(words)
      .join("text")
      .style("font-size", (d) => `${d.size}px`)
      .style("font-family", "sbM")
      .style("fill", () => d3.schemeCategory10[Math.floor(Math.random() * 10)])
      .attr("text-anchor", "middle")
      .attr("transform", (d) => `translate(${d.x}, ${d.y})rotate(${d.rotate})`)
      .text((d) => d.text);
  }

  return <svg ref={svgRef} width={width} height={height} />;
};

export default WordCloud;
