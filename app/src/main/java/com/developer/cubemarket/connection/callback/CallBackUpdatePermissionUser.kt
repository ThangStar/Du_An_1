package com.developer.cubemarket.connection.callback

import com.developer.cubemarket.fragment.bottom_sheet.BtsChangePermissionUserFragment

interface CallBackUpdatePermissionUser {
    fun onSuccess(context: BtsChangePermissionUserFragment, rs: String)
    fun onFail(rs: String)
    fun onError(rs: String)
}