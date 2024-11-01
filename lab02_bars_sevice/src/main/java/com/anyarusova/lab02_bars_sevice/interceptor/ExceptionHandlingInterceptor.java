package com.anyarusova.lab02_bars_sevice.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionHandlingInterceptor implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingInterceptor.class);


}
//    public override async Task<TResponse> UnaryServerHandler<TRequest, TResponse>(
//        TRequest request,
//        ServerCallContext context,
//        UnaryServerMethod<TRequest, TResponse> continuation)
//    {
//        try
//        {
//            return await continuation(request, context);
//        }
//        catch (RpcException rpcException)
//        {
//            LogException(rpcException, context?.Method);
//            throw;
//        }
//        catch (PostgresException px) when (IsUniqueConstraintViolation(px))
//        {
//            LogException(px, context.Method);
//            throw new RpcException(
//                new Status(StatusCode.FailedPrecondition, "Выбранные отправления уже запланированы")
//            );
//        }
//        catch (Exception ex)
//        {
//            LogException(ex, context?.Method);
//            throw new RpcException(new Status(GetStatus(ex), ex.Message));
//        }
//    }
//
//
//    private void LogException(Exception exception, string method)
//    {
//        var logLevel = GetExceptionLogLevel(exception);
//        _logger.Log(logLevel, exception, "gRPC execution error. Method:{Method}", method);
//    }
//
//    private static LogLevel GetExceptionLogLevel(Exception exception)
//    {
//        return exception switch
//        {
//            RpcException { StatusCode: StatusCode.NotFound } => LogLevel.Warning,
//            DomainException => LogLevel.Information,
//            OperationCanceledException => LogLevel.Information,
//            _ => LogLevel.Error
//        };
//    }
//
//    // TODO create own exceptions!
//    private static StatusCode GetStatus(Exception exception)
//    {
//        return exception switch
//        {
//            ArgumentOutOfRangeException => StatusCode.OutOfRange,
//            ArgumentNullException => StatusCode.InvalidArgument,
//            ArgumentException => StatusCode.InvalidArgument,
//            NotImplementedException => StatusCode.Unimplemented,
//            InvalidOperationException => StatusCode.FailedPrecondition,
//            DeliveryItemAlreadyInRouteSheetException => StatusCode.AlreadyExists,
//            DeliveryDateOutOfRangeException => StatusCode.InvalidArgument,
//            BatchSizeOverlimitedException => StatusCode.ResourceExhausted,
//            // TODO: доменные исключение для единообразия преобразовывать в FailedPrecondition
//            NotFoundException => StatusCode.NotFound,
//            DomainException => StatusCode.FailedPrecondition,
//            OperationCanceledException => StatusCode.Cancelled,
//            _ => StatusCode.Internal
//        };
//    }
//}