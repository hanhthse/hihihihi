<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .fa-2x {
        font-size: 40px;
    }
    .status-unconfirm {
        background-color: #1e90ff; /* Màu xanh biển */
        color: white;
    }

    .status-confirm {
        background-color: #ffdb58; /* Màu vàng */
        color: black;
    }

    .status-progress {
        background-color: #00ff00; /* Màu xanh lá */
        color: black;
    }

    .status-cancel {
        background-color: #ff0000; /* Màu đỏ */
        color: white;
    }

    .status-complete {
        background-color: #808080; /* Màu xám */
        color: white;
    }

    .status-button {
        border: none;
        padding: 5px 10px;
        cursor: pointer;
        border-radius: 5px;
    }

</style>
<div class="card mb-3">
    <div class="card-header">
        <i class="fas fa-table"></i>
        Table booking list
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Booking Id</th>
                        <th>User Name</th>
                        <th>Pet Name</th>
                        <th>Cage ID</th>
                        <th>Date Start</th>
                        <th>Date End</th>
                        <th>Price</th>
                        <th>Status</th>
                        <th>Image</th>
                        <th>Action</th>

                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>Booking Id</th>
                        <th>User Name</th>
                        <th>Pet Name</th>
                        <th>Cage ID</th>
                        <th>Date Start</th>
                        <th>Date End</th>
                        <th>Price</th>
                        <th>Status</th>
                        <th>Image</th>
                        <th>Action</th>

                    </tr>
                </tfoot>
                <tbody>
                    <c:forEach items="${listBookings}" var= "book">
                        <tr>
                            <!--Name-->
                            <td>${book.bookingId}</td>
                            <!--User name-->
                            <td>${book.userName}</td>
                            <!--Pet name-->
                            <td>${book.petName}</td>
                            <!--Cage Id-->
                            <td>${book.cageid}</td>
                            <!--Date Start-->
                            <td>${book.startDate}</td>
                            <!--Date End-->
                            <td>${book.endDate}</td>
                            <!--Date Price-->
                            <td>${book.totalCost}</td>
                            <!--Date status-->
                            <td>
                                <form action="dashboard?action=changeStatus" method="post">
                                    <input type="hidden" name="bookingId" value="${book.bookingId}"/> <!-- Giả sử bạn cần ID của booking để xử lý -->
                                    <button type="submit" class="status-button 
                                            ${book.status == 'unconfirm' ? 'status-unconfirm' : 
                                              book.status == 'confirm' ? 'status-confirm' :
                                              book.status == 'progress' ? 'status-progress' :
                                              book.status == 'cancel' ? 'status-cancel' :
                                              book.status == 'complete' ? 'status-complete' : ''}"
                                              ${book.status == 'complete' || book.status == 'cancel' ? 'disabled' : ''}>
                                                ${book.status}
                                            </button>
                                    </form>
                                </td>

                                <!--Image-->
                                <td>
                                    <img  width="100px"
                                          height="100px"
                                          src="${book.image}" 
                                          alt="${book.image}" class="card-img-top">
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${book.status != 'cancel' && book.status != 'complete'}">
                                            <!-- Nếu trạng thái không phải là "cancel" và 'complete', hiển thị biểu tượng Edit và Delete -->
                                            <!-- Edit -->
                                            <!--Edit-->
                                            <i class="fa fa-edit fa-2x"
                                               style="color: #469408"
                                               data-toggle="modal"
                                               data-target="#editBookModal"
                                               onclick="editBookModal(${book.bookingId},
                                                                    '${book.userName}',
                                                                    '${book.petName}',
                                                                    '${book.cageid}',
                                                                    '${book.startDate}',
                                                                    '${book.endDate}',
                                                                    '${book.totalCost}',
                                                                    '${book.status}',
                                                                    ${book.image})">
                                            </i>
                                            &nbsp;&nbsp;&nbsp;
                                            <!--Delete-->
                                            <i class="fa fa-trash fa-2x"
                                               style="color: #e70808"
                                               data-toggle="modal"
                                               data-target="#delete-modal"
                                               onclick="deleteBookModal(${book.bookingId})">
                                            </i>
                                        </c:when>
                                        <c:otherwise>
                                            <!-- Nếu trạng thái là "cancel", không hiển thị biểu tượng hoặc làm cho chúng không thể click -->
                                            <!-- Bạn có thể để trống hoặc hiển thị thông tin phù hợp tại đây -->
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
