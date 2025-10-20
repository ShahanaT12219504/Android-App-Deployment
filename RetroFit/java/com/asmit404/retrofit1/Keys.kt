package com.example.eversaleskotlinnewversion

/**
~  * Created by Vishal.Khanna on 28/09/21, 11:19 AM
~  * Copyright (c) 2021 . All rights reserved.
~  * Last modified 26/11/21, 11:32 AM
~  **/
object Keys {

    const val testURL = "https://test.evergreenpublications.in/"
    const val secureurl = "https://api.theroyalwaytosuccess.com/"
    const val KEY_TAG = "testkey"
    const val KEY_UserId = "UserId"
    const val DATABASE_NAME = "eversales.db"
    const val TABLE_BOOKS = "Books"
    const val TABLE_SS = "SS"
    const val TABLE_SS_SET = "SS_SET"
    const val TABLE_QtySet = "QtySet"
    const val KEY_Qty = "Qty"
    const val KEY_FinalAmount = "FinalAmount"
    const val KEY_BookTypeid = "BookTypeid"
    const val TABLE_PRINT_BILL = "PRINT_BILL"
    const val TABLE_RETURN_BOOKS = "ReturnBooks"
    const val TABLE_RETURN_ST = "ReturnST"
    const val TABLE_STATIONERY = "STATIONERY"
    const val KEY_ID = "id"
    const val KEY_BOOKID = "titleid"
    const val KEY_Title = "Title"
    const val KEY_OrderDate = "OrderDate"
    const val KEY_IsActive = "IsActive"
    const val KEY_Name = "Name"
    const val KEY_PSID = "PSID"
    const val KEY_BillId = "BillId"
    const val KEY_BillNoNew = "BillNoNew"
    const val KEY_date = "date"
    const val KEY_Rate = "Rate"
    const val KEY_ItemName = "ItemName"
    const val KEY_BillNo = "BillNo"
    const val KEY_InvoiceNo = "InvoiceNo"
    const val KEY_NoOfSets = "NoOfSets"
    const val KEY_TotalPrice = "TotalPrice"
    const val KEY_TotalAmount = "TotalAmount"
    const val KEY_AddedOn = "AddedOn"
    const val KEY_IsCompleteSet = "IsCompleteSet"
    const val KEY_IsReturn = "IsReturn"
    const val KEY_SetId = "SetId"
    const val KEY_SetNumber = "SetNumber"
    const val KEY_SetCode = "SetCode"
    const val KEY_Quantity = "Quantity"
    const val KEY_MinQuantity = "MinQuantity"
    const val KEY_quantity = "quantity"
    const val KEY_Price = "Price"
    const val KEY_price = "price"
    const val KEY_Payment = "Payment"
    const val KEY_payment = "payment"
    const val KEY_GrossPrice = "GrossPrice"
    const val KEY_TotalValue = "TotalValue"
    const val KEY_SGST = "SGST"
    const val KEY_CGST = "CGST"
    const val KEY_IGST = "IGST"
    const val KEY_HSM = "HSM"
    const val KEY_PublisherId = "PublisherId"
    const val KEY_ProductName = "ProductName"
    const val KEY_ProductCode = "ProductCode"
    const val KEY_ProductTypeId = "ProductTypeId"
    const val KEY_PId = "PId"
    const val KEY_pid = "pid"
    const val KEY_ClassId = "ClassId"
    const val KEY_Class = "Class"
    const val KEY_ProductTypeID = "ProductTypeID"
    const val KEY_ProductType = "ProductType"
    const val KEY_PaymentType = "PaymentType"
    const val KEY_PaymentTypeId = "PaymentTypeId"
    const val KEY_SchoolId = "SchoolId"
    const val KEY_schoolid = "schoolid"
    const val KEY_school = "school"
    const val KEY_Schoolid = "Schoolid"
    const val KEY_PublId = "PublId"
    const val KEY_Address = "Address"
    const val KEY_Area = "Area"
    const val KEY_IsDiscount = "IsDiscount"
    const val KEY_Discount = "Discount"
    const val KEY_Qunatity = "Qunatity"
    const val KEY_PinCode = "PinCode"
    const val KEY_ReturnReason = "ReturnReason"
    const val KEY_BoardId = "BoardId"
    const val KEY_AreaCode = "AreaCode"
    const val KEY_Board = "Board"
    const val KEY_StockOut = "StockOut"
    const val KEY_Phone = "Phone"
    const val KEY_Email = "Email"
    const val KEY_HeadId = "HeadId"
    const val KEY_RoleId = "RoleId"
    const val KEY_Role = "Role"
    const val KEY_gprice = "gprice"
    var bill_list = 0
    var book_arr_size = 0
    var op_arr_size = 0
    var st_arr_size = 0

    //new added
    const val NAMESPACE: String = "http://test.evergreenpublications.in/"
    const val URL_LOG_EXT: String = "ESale/ES/Login/"
    const val URL_LIST_EXT: String = "ESale/SIn/SN/"
    const val URL_CreateU: String = NAMESPACE + URL_LOG_EXT + "CreateUser"
    const val URL_RoleList: String = NAMESPACE + URL_LOG_EXT + "RoleList"
    const val URL_HeadList: String = NAMESPACE + URL_LOG_EXT + "HeadList"
    const val URL_GetAllList: String = NAMESPACE + URL_LOG_EXT + "GetAllList"
    const val URL_SchoolList: String = NAMESPACE + URL_LIST_EXT + "SchoolList"
    const val URL_SchoolListWithName: String =
        "http://test.evergreenpublications.in/ESale/SIN/sN/SchoolListWithName"
    const val URL_PublisherList: String = NAMESPACE + URL_LIST_EXT + "PublisherList"
    const val URL_ReturnBillsList: String = NAMESPACE + "ESale/SO/SNV/ReturnBillsList"
    const val URL_ListOfBills: String = NAMESPACE + "ESale/SO/SNV/ListOfBills" //
    const val URL_TodayProductBillByLocation: String =
        "http://test.evergreenpublications.in/ESale/SOR/SNV/TodayProductBillByLocation"
    const val URL_TotalProductBillByLocation: String =
        "http://test.evergreenpublications.in/ESale/SOR/SNV/TotalProductBillByLocation"
    const val URL_TotalProductBillByLocationDate: String =
        "http://test.evergreenpublications.in/ESale/SOR/SNV/TotalProductBillByLocationDate"
    const val URL_BillListByLocation: String =
        "http://test.evergreenpublications.in/ESale/SOR/SNV/BillListByLocation"
    const val URL_BillReportByDate: String = NAMESPACE + "ESale/SO/SNV/BillReportByDate"
    const val URL_ProductList: String = NAMESPACE + URL_LIST_EXT + "ProductList"
    const val URL_CheckAreaCode: String =
        "http://test.evergreenpublications.in/ESale/SIn/SN/CheckAreaCode"
    const val URL_GetSetList: String = NAMESPACE + URL_LIST_EXT + "GetSetList"
    const val URL_ClassList: String = NAMESPACE + URL_LIST_EXT + "ClassList"
    const val URL_AvailableStock: String =
        "http://test.evergreenpublications.in/ESale/SIN/SN/AvailableStock"
    const val URL_GetUserById: String = NAMESPACE + URL_LOG_EXT + "GetUserById"
    const val URL_SchoolListById: String = NAMESPACE + URL_LIST_EXT + "SchoolListById"
    const val URL_PublisherListById: String = NAMESPACE + URL_LIST_EXT + "PublisherListById"
    const val URL_ProductListById: String = NAMESPACE + URL_LIST_EXT + "ProductListById"
    const val URL_ProductListById1: String =
        "http://test.evergreenpublications.in/ESale/SIN/SN/ProductListById1"
    const val URL_BillDetail: String = NAMESPACE + "ESale/SO/SNV/BillDetail"
    const val URL_SearchBillByNumber: String = NAMESPACE + "ESale/SO/SNV/SearchBillByNumber"
    const val URL_FindBill: String = NAMESPACE + "ESale/SO/SNV/FindBill"
    const val URL_GetSetId: String = NAMESPACE + URL_LIST_EXT + "GetSetId"
    const val URL_BindProductType: String =
        "http://test.evergreenpublications.in/ESale/SIn/SN/BindProductType"
    const val URL_ClassByProductType: String =
        "http://test.evergreenpublications.in/ESale/SIn/SN/ClassByProductType"
    const val URL_ProductListByType: String =
        "http://test.evergreenpublications.in/ESale/SIn/SN/ProductListByType"
    const val URL_GetPaymentType: String = NAMESPACE + "ESale/SO/SNV/GetPaymentType"
    const val URL_MLogin: String = NAMESPACE + URL_LOG_EXT + "MLogin/"
    const val URL_CheckBillExist: String =
        "http://test.evergreenpublications.in/ESale/SO/SNV/CheckBillExist"
    const val URL_BoardList: String = NAMESPACE + URL_LIST_EXT + "BoardList"
    const val URL_GetLastSet: String = NAMESPACE + URL_LIST_EXT + "GetLastSet"
    const val URL_GetSetBookList: String = NAMESPACE + URL_LIST_EXT + "GetSetBookList"
    const val URL_StockReturnListBySchoolClass: String =
        "http://test.evergreenpublications.in/ESale/SOR/SNV/StockReturnListBySchoolClass"
    const val URL_ProductType: String = NAMESPACE + URL_LIST_EXT + "ProductType"
    const val BASE_URL: String = "https://evergreenpublications.in/"
    const val KEY_pre_msge: String = "Please wait a moment "
    const val KEY_internetmsg: String = "Please connect to a working network connection!"
    const val KEY_Exceptionmsg1: String = "Sorry for the inconvenience we are under maintenance"
    const val KEY_SH: String = "UserDetails"
    var statusbar_change: Int = 0
    const val PERMISSION_BLUETOOTH: Int = 1
    //new added


}