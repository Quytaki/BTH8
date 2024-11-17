# Bài tập 1: Lưu trữ và Truy xuất Dữ Liệu Đơn Giản

## - Mô tả: Tạo một ứng dụng Android với một màn hình đơn giản chứa hai nút và
một EditText.
• Nút 1: "Lưu dữ liệu" - Khi người dùng nhập một chuỗi vào EditText và nhấn
nút, chuỗi này sẽ được lưu vào SharedPreferences.
• Nút 2: "Lấy dữ liệu" - Khi người dùng nhấn nút này, ứng dụng sẽ lấy dữ liệu
đã lưu từ SharedPreferences và hiển thị trong một Toast.
- Yêu cầu:
• Sử dụng SharedPreferences để lưu và truy xuất dữ liệu.
• Kiểm tra xem dữ liệu có tồn tại trong SharedPreferences không trước khi lấy
ra.
# Bài tập 2: Lưu và Đọc Cài Đặt Người Dùng
## - Mô tả: Tạo một ứng dụng cho phép người dùng lưu các cài đặt như kích thước chữ
và chế độ tối (dark mode).
• Có hai tuỳ chọn Switch cho phép người dùng bật/tắt chế độ tối và điều chỉnh
kích thước chữ (nhỏ, vừa, lớn).
• Khi người dùng điều chỉnh các cài đặt này và thoát ứng dụng, các cài đặt sẽ
được lưu trữ lại trong SharedPreferences.
• Khi mở lại ứng dụng, các cài đặt sẽ được khôi phục tự động từ
SharedPreferences.
- Yêu cầu:
• Sử dụng SharedPreferences để lưu các trạng thái của Switch.
• Đảm bảo cài đặt của người dùng vẫn được giữ lại sau khi ứng dụng tắt đi và
mở lại.
# Bài tập 3: Đếm Số Lần Mở Ứng Dụng
## - Mô tả: Tạo một ứng dụng có khả năng đếm và hiển thị số lần người dùng đã mở
ứng dụng.
• Mỗi khi ứng dụng được mở, số lần mở sẽ được tăng lên và hiển thị trên màn
hình.
• Dữ liệu về số lần mở này sẽ được lưu lại bằng SharedPreferences để đảm bảo
khi người dùng thoát và mở lại, dữ liệu vẫn được giữ nguyên.
- Yêu cầu:
• Sử dụng SharedPreferences để lưu lại số lần mở ứng dụng.
• Mỗi khi mở ứng dụng, dữ liệu được lấy ra từ SharedPreferences và tăng lên
trước khi hiển thị.
# Bài tập 4: Tạo Màn Hình Đăng Nhập Với SharedPreferences
## - Mô tả: Tạo một ứng dụng có màn hình đăng nhập đơn giản.
• Người dùng nhập tên người dùng và mật khẩu, sau đó nhấn nút "Đăng nhập".
• Khi đăng nhập thành công, ứng dụng sẽ lưu trạng thái đăng nhập (đã đăng
nhập) vào SharedPreferences.
• Khi mở lại ứng dụng, nếu người dùng đã đăng nhập trước đó, sẽ bỏ qua màn
hình đăng nhập và chuyển thẳng vào màn hình chính.
• Có nút "Đăng xuất" trên màn hình chính để xóa trạng thái đăng nhập khỏi
SharedPreferences.
- Yêu cầu:
• Sử dụng SharedPreferences để lưu và kiểm tra trạng thái đăng nhập của người
dùng.
• Sử dụng SharedPreferences.Editor để xoá dữ liệu khi người dùng đăng xuất.
# Bài tập 5: Tạo Ứng Dụng Ghi Nhớ Tác Vụ Với SharedPreferences
## - Mô tả: Tạo một ứng dụng cho phép người dùng nhập một danh sách tác vụ đơn
giản.
• Người dùng có thể thêm, xóa hoặc chỉnh sửa tác vụ. Danh sách các tác vụ sẽ
được lưu vào SharedPreferences để có thể khôi phục khi ứng dụng mở lại.
• Các tác vụ được lưu trữ dưới dạng JSON trong SharedPreferences.
- Yêu cầu:
• Sử dụng Gson hoặc JSONArray để lưu và đọc danh sách tác vụ từ
SharedPreferences.
• Đảm bảo khi người dùng thoát và mở lại ứng dụng, danh sách tác vụ vẫn
được lưu lại đầy đủ
