import { HttpClient, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AuthService } from "./auth.service";
import { catchError, Observable, switchMap, throwError } from "rxjs";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  constructor(private auth: AuthService, private http: HttpClient) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.auth.getToken();

    if (token) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    }

    return next.handle(req).pipe(
      catchError((error) => {
        if (error.status === 401 && this.auth.getRefreshToken()) {
          // Rinnova il token
          return this.http
            .post<{ token: string }>('http://localhost:8080/api/auth/refresh-token', {
              refreshToken: this.auth.getRefreshToken(),
            })
            .pipe(
              switchMap((response) => {
                this.auth.saveToken(response.token);
                req = req.clone({
                  setHeaders: {
                    Authorization: `Bearer ${response.token}`,
                  },
                });
                return next.handle(req);
              }),
              catchError((err) => {
                this.auth.logout();
                return throwError(err);
              })
            );
        }
        return throwError(error);
      })
    );
  }
}