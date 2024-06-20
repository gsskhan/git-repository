Sub FilterAndInsertLengthColumns()
    Dim ws As Worksheet
    Set ws = ThisWorkbook.Sheets("Sheet1") ' Change "Sheet1" to your sheet name

    Dim headerRange As Range
    Dim headerCell As Range
    Dim keepHeaders As Variant
    Dim insertAfterHeaders As Variant
    Dim lastRow As Long
    Dim i As Long, j As Long

    ' Define headers to keep and to insert after
    keepHeaders = Array("Header1", "Header2", "Header3") ' Change these to the headers you want to keep
    insertAfterHeaders = Array("Header1", "Header2") ' Change these to the headers after which you want to insert new columns

    ' Find the last row with data in column A
    lastRow = ws.Cells(ws.Rows.Count, 1).End(xlUp).Row

    ' Loop through columns and hide the ones not in the keepHeaders array
    For Each headerCell In ws.Range("1:1").Cells
        If Not IsInArray(headerCell.Value, keepHeaders) Then
            ws.Columns(headerCell.Column).EntireColumn.Hidden = True
        End If
    Next headerCell

    ' Delete hidden columns
    ws.Cells.EntireColumn.Hidden = False
    For Each headerCell In ws.Range("1:1").Cells
        If headerCell.EntireColumn.Hidden Then
            headerCell.EntireColumn.Delete
        End If
    Next headerCell

    ' Loop through the specified headers and insert new columns
    For i = UBound(insertAfterHeaders) To LBound(insertAfterHeaders) Step -1
        For Each headerCell In ws.Range("1:1").Cells
            If headerCell.Value = insertAfterHeaders(i) Then
                ' Insert new column after the specified header
                ws.Columns(headerCell.Column + 1).Insert Shift:=xlToRight

                ' Loop through each cell in the column to calculate and insert the length
                For j = 2 To lastRow
                    ws.Cells(j, headerCell.Column + 1).Value = Len(ws.Cells(j, headerCell.Column).Value)
                Next j
            End If
        Next headerCell
    Next i
End Sub

Function IsInArray(value As Variant, arr As Variant) As Boolean
    Dim element As Variant
    For Each element In arr
        If element = value Then
            IsInArray = True
            Exit Function
        End If
    Next element
    IsInArray = False
End Function
