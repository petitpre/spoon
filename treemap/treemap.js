google.load("visualization", "1.1", {packages: ["treemap"]});
google.setOnLoadCallback(drawChart);

function drawChart() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'ID');
    data.addColumn('string', 'Parent');
    data.addColumn('number', 'Number Of Lines');
    data.addColumn('number', 'Line rate');
    data.addColumn('string', 'Fullname');
    data.addRows(datas);

    var tree = new google.visualization.TreeMap(document.getElementById('chart_div'));

    var options = {
        highlightOnMouseOver: true,
        maxDepth: 1,
        maxPostDepth: 2,
        showScale: true,
        height: 800,
        useWeightedAverageForAggregation: true,
        generateTooltip: showTooltip
    };

    function showTooltip(row, size, value) {
        return '<div style="background:#fd9; padding:10px; border-style:solid">' +
            data.getValue(row, 4)         + '</div>';
    }

    tree.draw(data, options);


}