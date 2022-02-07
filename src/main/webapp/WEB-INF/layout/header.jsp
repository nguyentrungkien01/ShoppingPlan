<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="main-header">
	<!-- Logo Header -->
	<div class="logo-header" data-background-color="dark2">
		<a class="logo" href="">name</a>
		<button class="navbar-toggler sidenav-toggler ml-auto" type="button" data-toggle="collapse"
			data-target="collapse" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon">
				<i class="fas fa-bars"></i>
			</span>
		</button>
		<button class="topbar-toggler more">
			<i class="fas fa-ellipsis-v"></i>
		</button>
		<div class="nav-toggle">
			<button class="btn btn-toggle toggle-sidebar">
				<i class="fas fa-bars"></i>
			</button>
		</div>
	</div>
	<!-- End Logo Header -->
	<!-- Navbar Header -->
	<nav class="navbar navbar-header navbar-expand-lg" data-background-color="dark">
		<div class="container-fluid">
			<ul class="navbar-nav topbar-nav ml-md-auto align-items-center">

				<li class="nav-item dropdown hidden-caret">
					<a class="nav-link" data-toggle="dropdown" href="javascript:;" aria-expanded="false">
						<i class="fas fa-layer-group"></i>
					</a>
					<div class="dropdown-menu quick-actions quick-actions-info animated fadeIn">
						<div class="quick-actions-header">
							<span class="title mb-1">Lựa chọn nhanh</span>
							<span class="subtitle op-8">Shortcuts</span>
						</div>
						<div>
							<div class="quick-actions-items">
								<div class="row m-0">
									<a class="col-6 col-md-6 p-0" href="/admin/statisticview">
										<div class="quick-actions-item">
											<i class="flaticon-file-1"></i>
											<span class="text">Thống kê</span>
										</div>
									</a>
									<a class="col-6 col-md-6 p-0" href="/admin/reportview">
										<div class="quick-actions-item">
											<i class="flaticon-database"></i>
											<span class="text">Báo cáo</span>
										</div>
									</a>
									<a class="col-12 p-0" href="/admin/rulemodel">
										<div class="quick-actions-item">
											<i class="flaticon-database"></i>
											<span class="text">Quy định chung</span>
										</div>
									</a>
								</div>
							</div>
						</div>
					</div>
				</li>

				<li class="nav-item dropdown hidden-caret">
					<a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="javascript:;"
						aria-expanded="false">
						<div class="avatar-sm">
							<img src="https://res.cloudinary.com/ouproject/image/upload/v1641740539/avatar_account/default_avatar_mvuqgu_vigdu2.png"
								alt="avatar user" class="avatar-img rounded-circle">
						</div>
					</a>
					<ul class="dropdown-menu dropdown-user animated fadeIn">
						<div class="dropdown-user-scroll scrollbar-outer">
							<li>
								<div class="user-box">
									<div class="avatar-lg">
										<img src="https://res.cloudinary.com/ouproject/image/upload/v1641740539/avatar_account/default_avatar_mvuqgu_vigdu2.png"
											alt="image profile" class="avatar-img rounded">
									</div>
									<div class="u-text">
										<h4>
										user name
										</h4>
										<a href="/admin/profileview" class="btn btn-xs btn-secondary btn-sm">Thông tin
											cá nhân</a>
									</div>
								</div>
							</li>
							<li>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="/admin/changepasswordview/">Đổi mật khẩu</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="/admin/logoutview">Đăng xuất</a>
							</li>
						</div>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
	<!-- End Navbar -->
</div>