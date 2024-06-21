Sub CreatePivotTableWithMinMax()
    Dim ws As Worksheet
    Dim newWs As Worksheet
    Dim pivotWs As Worksheet
    Dim lastRow As Long
    Dim lastCol As Long
    Dim searchColumns As Variant
    Dim foundRange As Range
    Dim i As Integer
    Dim minMaxValues As Range
    Dim minMaxAddress As String
    
    ' Define the columns to search for
    searchColumns = Array("Column1", "Column2") ' Update with your column headers
    
    ' Set the worksheet with data
    Set ws = ThisWorkbook.Sheets("Sheet1") ' Update with your sheet name
    
    ' Find the last row and column with data
    lastRow = ws.Cells(ws.Rows.Count, 1).End(xlUp).Row
    lastCol = ws.Cells(1, ws.Columns.Count).End(xlToLeft).Column
    
    ' Create a new sheet for min/max values
    Set newWs = ThisWorkbook.Sheets.Add
    newWs.Name = "MinMaxValues"
    
    ' Loop through the search columns and find min/max values
    For i = LBound(searchColumns) To UBound(searchColumns)
        Set foundRange = ws.Rows(1).Find(What:=searchColumns(i), LookIn:=xlValues, LookAt:=xlWhole)
        If Not foundRange Is Nothing Then
            ' Get the min/max values
            newWs.Cells(i + 1, 1).Value = searchColumns(i)
            newWs.Cells(i + 1, 2).Value = WorksheetFunction.Min(ws.Range(foundRange.Offset(1, 0), ws.Cells(lastRow, foundRange.Column)))
            newWs.Cells(i + 1, 3).Value = WorksheetFunction.Max(ws.Range(foundRange.Offset(1, 0), ws.Cells(lastRow, foundRange.Column)))
        End If
    Next i
    
    ' Set the range for the pivot table
    Set minMaxValues = newWs.Range(newWs.Cells(1, 1), newWs.Cells(UBound(searchColumns) + 1, 3))
    minMaxAddress = minMaxValues.Address(ReferenceStyle:=xlR1C1)
    
    ' Create a new sheet for the pivot table
    Set pivotWs = ThisWorkbook.Sheets.Add
    pivotWs.Name = "PivotTable"
    
    ' Create the pivot table
    With ThisWorkbook.PivotTableWizard
        .PivotCache.CreatePivotTable TableDestination:=pivotWs.Range("A3"), TableName:="PivotTable1", DefaultVersion:=xlPivotTableVersion15
        .PivotTableWizard SourceData:=newWs.Name & "!" & minMaxAddress, TableDestination:=pivotWs.Range("A3")
    End With
    
    ' Add fields to the pivot table
    With pivotWs.PivotTables("PivotTable1")
        .PivotFields("Column1").Orientation = xlRowField
        .PivotFields("Column2").Orientation = xlRowField
        .AddDataField .PivotFields("Min"), "Min Value", xlMin
        .AddDataField .PivotFields("Max"), "Max Value", xlMax
    End With

    ' Format the pivot table
    pivotWs.PivotTables("PivotTable1").ShowTableStyleRowStripes = True
    pivotWs.PivotTables("PivotTable1").ShowTableStyleColumnStripes = True
End Sub
