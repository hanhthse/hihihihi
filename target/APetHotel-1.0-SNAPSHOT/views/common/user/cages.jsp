<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Giá Dịch Vụ Chăm Sóc Chó</title>
        <style>
            .services-container {
                display: flex;
                flex-wrap: wrap;
                justify-content: center; /* Các blocks sẽ được căn giữa */
                gap: 20px; /* Khoảng cách giữa các blocks */
            }

            .service-block {
                background-color: #fca311;
                border-radius: 8px;
                padding: 20px;
                font-family: Arial, sans-serif;
                color: #ffffff;
                flex: 0 1 calc(30% - 70px); /* Giảm kích thước một chút so với 1/3 để tạo khoảng cách */

                box-sizing: border-box; /* Đảm bảo padding không làm tăng kích thước block */
                display: flex;
                flex-direction: column;
                align-items: center; /* Căn giữa các thành phần theo chiều dọc */
            }

            /* Tiếp tục với phần còn lại của CSS */
            .service-title {
                font-size: 24px; /* Cỡ chữ lớn */
                margin-bottom: 5px; /* Khoảng cách dưới */
            }

            .service-pricing {
                text-align: center; /* Căn giữa giá cả */
            }

            .current-price {
                font-size: 30px; /* Cỡ chữ giá hiện tại */
                font-weight: bold; /* Đậm */
            }

            .old-price {
                text-decoration: line-through; /* Gạch ngang */
                color: #6c757d; /* Màu chữ giảm giá */
                padding-left: 10px; /* Khoảng cách giữa giá cũ và giá mới */
            }

            .per-day {
                display: block; /* Hiển thị dòng mới */
            }

            .dish-image,
            .bone-image {
                display: block; /* Hiển thị dòng mới */
                margin: 10px auto; /* Căn giữa */
            }

            .service-details {
                list-style: none; /* Không có dấu đầu dòng */
                padding: 0; /* Không có padding */
                margin: 10px 0; /* Khoảng cách trên dưới */
            }

            .service-details li {
                background: url('path_to_your_dot_image.png') no-repeat left center; /* Hình ảnh dấu chấm */
                padding-left: 20px; /* Khoảng cách cho dấu chấm */
                margin-bottom: 10px; /* Khoảng cách giữa các dòng */
            }

            .contact-button {
                background-color: #e5e5e5; /* Màu nền nút */
                border: none; /* Không viền */
                padding: 10px 20px; /* Padding nút */
                border-radius: 5px; /* Bo tròn góc nút */
                color: #000000; /* Màu chữ */
                font-weight: bold; /* Đậm */
                text-transform: uppercase; /* Chữ hoa */
                cursor: pointer; /* Con trỏ chuột */
                margin-top: 20px; /* Khoảng cách nút với phần trên */
                display: block; /* Hiển thị dòng mới */
                width: 100%; /* Chiều rộng tối đa */
            }
            /* ... */
            .imge2 img{
                width: 20%
            }
            @media (max-width: 768px) { /* Điều chỉnh cho các màn hình nhỏ hơn (tablet, mobile) */
                .service-block {
                    flex: 0 1 calc(50% - 20px); /* Trên màn hình nhỏ, hiển thị 2 sản phẩm trên mỗi hàng */
                    max-width: calc(50% - 20px);
                }
            }

            @media (max-width: 480px) { /* Điều chỉnh cho các màn hình rất nhỏ (điện thoại di động) */
                .service-block {
                    flex: 0 1 100%; /* Trên màn hình nhỏ nhất, hiển thị 1 sản phẩm trên mỗi hàng */
                    max-width: 100%;
                }
            }
            .anh {
                display: flex;
                justify-content: space-between; /* Sẽ đẩy hai ảnh ra hai bên */
            }

            .imge2 {
                width: 90px;
                height: auto;
            }

            /* Nếu bạn muốn hai ảnh cách đều nhau và cách đều hai bên viền của khối 'anh', thêm padding cho chúng */
            .dish-image {
                margin-right: 200px; /* Khoảng cách giữa ảnh món ăn và viền/phần tử tiếp theo */
            }

            .bone-image {
                margin-left: 10px; /* Khoảng cách giữa ảnh hình xương và viền/phần tử trước đó */
            }

        </style>
    </head>
    <body>
        <div class="services-container">
            <c:forEach items="${listCages}" var="cage">
                <div class="service-block">
                    <h2 class="service-title">${cage.description}</h2>
                    <div class="service-pricing">
                        <span class="current-price">Giá mỗi ngày: ${cage.pricePerDay} VND</span>
                    </div>
                    <div class="anh">
                        <img class="imge2 dish-image" src="https://fagopet.vn/tassets/images/img_3.png" alt="Món Ăn">
                        <img class="imge2 bone-image" src="https://fagopet.vn/tassets/images/img_4.png" alt="Hình Xương">
                    </div>


                    <ul class="service-details">
                        <li>Camera theo dõi 24/24</li>
                        <li>2-3 bữa mỗi ngày</li>
                        <li>Vệ sinh chuồng mỗi ngày</li>
                        <li>Vui chơi mỗi ngày 2 lần</li>
                        <li>Cung cấp hình ảnh và video của các bé mỗi ngày</li>
                    </ul>
                    <button type="button" class="contact-button">Liên hệ tư vấn</button>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
