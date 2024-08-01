package com.example.banhang.ui.base

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

open class BaseDialogFragment : DialogFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        // Xử lý các sự kiện click ở đây nếu cần
    }

    // Được gọi sau khi activity của fragment đã được tạo. Tại đây, bạn có thể thực hiện các thao tác liên quan đến activity.
    override fun onActivityCreated(arg0: Bundle?) {
        super.onActivityCreated(arg0)
        // Thực hiện các thao tác liên quan đến activity hoặc cập nhật UI nếu cần
    }

    //Phương thức này được gọi khi fragment được tạo. Bạn có thể khởi tạo dữ liệu hoặc thiết lập cấu hình ban đầu tại đây.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Khởi tạo các dữ liệu hoặc cấu hình tại đây nếu cần

    }

    // Được gọi khi fragment gắn vào activity. Bạn có thể sử dụng phương thức này để thực hiện các thao tác cần thiết với context.
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Có thể sử dụng context để lấy ViewModel hoặc thực hiện các tác vụ khác

    }

    // Được gọi khi fragment sẵn sàng để hiển thị. Bạn có thể sử dụng phương thức này để thực hiện các thao tác khi dialog bắt đầu.
    override fun onStart() {
        super.onStart()
    }

    //Phương thức này được gọi khi fragment cần tạo giao diện người dùng lần đầu tiên. Trong đoạn mã của bạn, nó cài đặt nền của dialog thành màu trong suốt.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        : Đặt nền của dialog thành trong suốt.
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

// Được gọi khi dialog sẵn sàng để hiển thị. Đoạn mã của bạn có thể điều chỉnh kích thước của dialog ở đây
    override fun onResume() {
        super.onResume()
        val display = resources.displayMetrics
        val width = display.widthPixels
        if (null != dialog) {
            // Bạn có thể điều chỉnh kích thước dialog nếu cần

//            dialog!!.window!!.setLayout(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            )
        }
    }

    // Được gọi khi fragment không còn hiển thị. Bạn có thể thực hiện các thao tác khi dialog không còn trên màn hình.
    override fun onStop() {
        super.onStop()
        // Giải phóng tài nguyên nếu cần
    }

    //Được gọi khi fragment bị hủy. Bạn có thể giải phóng tài nguyên tại đây.
    override fun onDestroy() {
        super.onDestroy()
        // Giải phóng các tài nguyên nếu cần

    }

    // Được gọi khi dialog bị đóng. Bạn có thể thực hiện các thao tác cần thiết tại đây, chẳng hạn như cập nhật dữ liệu hoặc thông báo cho người dùng.
    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        // Thực hiện các thao tác khi dialog bị đóng

    }

    // Phương thức này được ghi đè để đảm bảo rằng việc hiển thị dialog sẽ không bị mất điều kiện hiện tại.
    override fun show(manager: FragmentManager, tag: String?) {
        val ft = manager.beginTransaction()
        ft.add(this, tag)
        ft.commitAllowingStateLoss()
        // Có thể thực hiện các thao tác trước khi hiển thị dialog

    }

    override fun dismiss() {
        super.dismiss()
        // Thực hiện các thao tác trước khi đóng dialog

    }



}